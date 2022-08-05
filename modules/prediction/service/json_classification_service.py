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

import logging
from concurrent import futures

import grpc
import pandas as pd
import tensorflow as tf

import json_classification_service_pb2 as jcs
import json_classification_service_pb2_grpc as jcs_grpc
from json_classifier import JSONClassifier


class JSONClassificationService(jcs_grpc.JSONClassificationService):

    def __init__(self, dataframe_path, trained_model_path):
        self.dataframe = pd.read_csv(dataframe_path, sep=";")
        self.json_classifier = JSONClassifier(self.dataframe)
        self.trained_model = tf.keras.models.load_model(trained_model_path)

    def getPrediction(self, request, context):
        code = request.iban[:2].upper()
        size = len(request.iban)
        return jcs.OutputLabel(
            json=self.json_classifier.predict(
                trained_model=self.trained_model,
                code=code,
                size=size))


def main():
    df_path = "../../training/data/country_validation_json.csv"
    model_path = "../../training/data/json_classification_model"
    listening_port = "[::]:41151"
    jsc = JSONClassificationService(dataframe_path=df_path,
                                    trained_model_path=model_path)
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    jcs_grpc.add_JSONClassificationServiceServicer_to_server(jsc, server)
    server.add_insecure_port(listening_port)
    server.start()
    server.wait_for_termination()


if __name__ == "__main__":
    logging.basicConfig()
    main()
