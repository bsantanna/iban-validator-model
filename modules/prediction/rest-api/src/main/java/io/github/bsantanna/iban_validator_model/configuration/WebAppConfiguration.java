/*

Copyright 2022 Bruno César Brito Sant'Anna <mail@bsantanna.me>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

package io.github.bsantanna.iban_validator_model.configuration;

import io.github.bsantanna.iban_validator_model.grpc.JsonClassificationServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

  @Bean
  Channel getManagedChannel(@Value("${grpc.service.address}") String target) {
    return ManagedChannelBuilder.forTarget(target).usePlaintext().build();
  }

  @Bean
  JsonClassificationServiceGrpc.JSONClassificationServiceBlockingStub getBlockingStub(Channel channel) {
    return JsonClassificationServiceGrpc.newBlockingStub(channel);
  }

}
