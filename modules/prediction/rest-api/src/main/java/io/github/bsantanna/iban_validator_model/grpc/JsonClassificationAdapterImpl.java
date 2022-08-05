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

package io.github.bsantanna.iban_validator_model.grpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bsantanna.iban_validator_model.model.JsonClassification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonClassificationAdapterImpl
  implements JsonClassificationAdapter {
  private final JsonClassificationServiceGrpc.JSONClassificationServiceBlockingStub blockingStub;

  private final ObjectMapper objectMapper;

  @SneakyThrows
  public JsonClassification getPrediction(String iban) {
    var request = InputFeatures.newBuilder().setIban(iban).build();
    var response = blockingStub.getPrediction(request);
    return objectMapper.readValue(response.getJson().getBytes(), JsonClassification.class);
  }

}
