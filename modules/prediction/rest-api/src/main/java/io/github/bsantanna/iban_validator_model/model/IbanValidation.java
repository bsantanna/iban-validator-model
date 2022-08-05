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

package io.github.bsantanna.iban_validator_model.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IbanValidation {

  private String iban;

  @JsonProperty("is_valid")
  private Boolean isValid;

  private String description;

  @JsonProperty("classification")
  private JsonClassification jsonClassification;

}
