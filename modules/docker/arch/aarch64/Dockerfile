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

FROM armswdev/tensorflow-arm-neoverse:r22.09-tf-2.10.0-eigen

RUN sudo apt update -qq && \
    sudo apt install -yqq \
      python3-pandas openjdk-11-jre-headless && \
    sudo apt clean && \
    sudo rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
RUN python3 -m pip install protobuf==3.20.*

ADD prediction /opt/iban-validator-model/prediction
ADD training /opt/iban-validator-model/training

CMD /opt/iban-validator-model/prediction/container-entrypoint.sh
