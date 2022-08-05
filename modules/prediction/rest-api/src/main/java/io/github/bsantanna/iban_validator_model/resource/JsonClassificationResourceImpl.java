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

package io.github.bsantanna.iban_validator_model.resource;

import io.github.bsantanna.iban_validator_model.grpc.JsonClassificationAdapter;
import io.github.bsantanna.iban_validator_model.model.IbanValidation;
import io.github.bsantanna.iban_validator_model.model.JsonClassification;
import io.github.bsantanna.iban_validator_model.validation.IbanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonClassificationResourceImpl implements JsonClassificationResource {

  private final JsonClassificationAdapter jsonClassificationAdapter;

  private final IbanValidator ibanValidator;

  @Override
  public JsonClassification getPrediction(String iban) {
    return jsonClassificationAdapter.getPrediction(iban);
  }

  @Override
  public IbanValidation getValidation(String iban) {
    return ibanValidator.validate(iban);
  }

}
