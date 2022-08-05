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


import io.github.bsantanna.iban_validator_model.model.IbanValidation;
import io.github.bsantanna.iban_validator_model.model.JsonClassification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface JsonClassificationResource {

  @GetMapping(path = "/json-prediction", produces = MediaType.APPLICATION_JSON_VALUE)
  JsonClassification getPrediction(@RequestParam("iban") String iban);

  @GetMapping(path = "/validation", produces = MediaType.APPLICATION_JSON_VALUE)
  IbanValidation getValidation(@RequestParam("iban") String iban);

}