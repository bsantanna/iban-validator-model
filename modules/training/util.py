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


class Util:

    @staticmethod
    def encode_numerical_feature(feature, name, dataset):
        feature_ds = dataset.map(lambda x, y: x[name])
        feature_ds = feature_ds.map(lambda x: tf.expand_dims(x, -1))

        normalizer = tf.keras.layers.Normalization()
        normalizer.adapt(feature_ds)
        encoded_feature = normalizer(feature)

        return encoded_feature

    @staticmethod
    def encode_categorical_feature(feature, name, dataset, is_string=False, use_lookup=True, max_tokens=0):
        feature_ds = dataset.map(lambda x, y: x[name])
        feature_ds = feature_ds.map(lambda x: tf.expand_dims(x, -1))

        if use_lookup:
            encoding_layer_class = tf.keras.layers.StringLookup if is_string else tf.keras.layers.IntegerLookup
            encoding_layer = encoding_layer_class(output_mode="binary")
            encoding_layer.adapt(feature_ds)

        elif is_string:
            encoding_layer = tf.keras.layers.TextVectorization()
            encoding_layer.adapt(feature_ds)

        else:
            encoding_layer = tf.keras.layers.Hashing(num_bins=max_tokens, output_mode="one_hot")

        encoded_feature = encoding_layer(feature)
        return encoded_feature
