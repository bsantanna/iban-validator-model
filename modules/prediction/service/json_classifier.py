# Copyright 2022 Bruno César Brito Sant'Anna <mail@bsantanna.me>
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

import tensorflow as tf


class JSONClassifier:

    def __init__(self, dataframe):
        # assign dataframe
        self.dataframe = dataframe

    def predict(self, trained_model, code, size):
        prediction = trained_model.predict({
            "Code": tf.convert_to_tensor([code]),
            "Size": tf.convert_to_tensor([size])
        }, verbose=0)

        return self.dataframe["JSON"][prediction.argmax()]
