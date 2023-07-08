# Copyright 2023 Bruno César Brito Sant'Anna <mail@bsantanna.me>
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


import mlflow
import tensorflow as tf
from azureml.core import Workspace
from azureml.core.run import Run, _OfflineRun

from util import Util


class JSONClassificationModel:

    def __init__(self, dataframe):
        # assign dataframe
        self.dataframe = dataframe

        # initialize dataset
        features = dataframe.drop(["JSON"], axis=1)
        labels = tf.keras.utils.to_categorical(self.dataframe["JSON"].factorize()[0])
        dataset = tf.data.Dataset.from_tensor_slices((dict(features), labels))
        self.dataset = dataset.shuffle(buffer_size=len(dataframe))

    def build(self, batch_size):
        # update dataset with batch size
        self.dataset = self.dataset.batch(batch_size)

        # encode categorical feature
        feature_code = tf.keras.Input(shape=(1,), name="Code", dtype="string")
        feature_code_encoded = Util.encode_categorical_feature(feature_code, "Code", self.dataset, is_string=True)

        # encode numerical feature
        feature_size = tf.keras.Input(shape=(1,), name="Size", dtype="int64")
        feature_size_encoded = Util.encode_numerical_feature(feature_size, "Size", self.dataset)

        # declare inputs
        inputs = [
            feature_code,
            feature_size
        ]

        # create features encoded layer
        features_encoded = tf.keras.layers.concatenate([
            feature_code_encoded,
            feature_size_encoded
        ])

        # create input and output layers
        input_layer = tf.keras.layers.Dense(batch_size, activation="relu")(features_encoded)
        output_layer = tf.keras.layers.Dense(len(self.dataframe), activation="softmax")(input_layer)

        # create and compile the model
        model = tf.keras.Model(inputs, output_layer)
        model.compile("adam", "categorical_crossentropy", metrics=[
            "accuracy",
            tf.keras.metrics.Precision(),
            tf.keras.metrics.Recall()
        ])

        # plot model
        # tf.keras.utils.plot_model(model, show_shapes=True, show_dtype=True, rankdir="LR")

        return model

    def train(self, train_model, num_epochs):
        run = Run.get_context()
        ws = None
        if type(run) == _OfflineRun:
            ws = Workspace.from_config()
        else:
            ws = run.experiment.workspace

        mlflow.set_tracking_uri(ws.get_mlflow_tracking_uri())
        with mlflow.start_run():
            mlflow.tensorflow.autolog()
            train_model.fit(self.dataset, epochs=num_epochs, verbose=1)
