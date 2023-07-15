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


import argparse

from model import JSONClassificationModel
from util import Util


def main():
    # hyper parameters

    parser = argparse.ArgumentParser()
    parser.add_argument('--batch-size', type=int,
                        dest='batch_size', help='Batch sampling size')
    parser.add_argument('--num-epochs', type=int,
                        dest='num_epochs', help='Number of training epochs')
    parser.add_argument('--input-activation', type=str,
                        dest='input_activation', help='Input layer activation function')
    parser.add_argument('--output-activation', type=str,
                        dest='output_activation', help='Output layer activation function')
    parser.add_argument('--optimizer', type=str,
                        dest='optimizer', help='Optimizer backpropagation function')
    parser.add_argument('--loss', type=str,
                        dest='loss', help='Error loss backpropagation function')
    args = parser.parse_args()

    # load structured data
    country_validation_json = Util.load_data()

    # initialize model with structured data
    json_classification_model = JSONClassificationModel(dataframe=country_validation_json)

    keras_model = json_classification_model.build(
        batch_size=args.batch_size,
        input_activation=args.input_activation,
        output_activation=args.output_activation,
        optimizer=args.optimizer,
        loss=args.loss)

    # train model with given number of epochs and save result
    json_classification_model.train(keras_model, args.num_epochs)


if __name__ == "__main__":
    main()
