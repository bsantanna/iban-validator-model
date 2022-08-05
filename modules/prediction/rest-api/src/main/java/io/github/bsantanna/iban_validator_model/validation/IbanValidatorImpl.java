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

package io.github.bsantanna.iban_validator_model.validation;

import io.github.bsantanna.iban_validator_model.grpc.JsonClassificationAdapter;
import io.github.bsantanna.iban_validator_model.model.IbanValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IbanValidatorImpl implements IbanValidator {

  private final JsonClassificationAdapter jsonClassificationAdapter;

  private final Map<Character, Integer> charReplacementMap = getCharReplacementMap();

  @Override
  public IbanValidation validate(String iban) {
    var bban = iban.substring(4);
    var code = iban.substring(0, 2).toUpperCase();
    var size = iban.length();
    var checkDigit = iban.substring(2, 4);

    var jsonClassification = jsonClassificationAdapter.getPrediction(iban);

    boolean isValid = true;
    String description = "IBAN passed validation";

    if (!jsonClassification.getCode().equals(code)) {
      isValid = false;
      description = "The predicted code is different from input code.";
    } else if (!jsonClassification.getSize().equals(size)) {
      isValid = false;
      description = "The predicted size is different from input length.";
    } else if (!validateBbanRegex(jsonClassification.getBbanRegex(), bban)) {
      isValid = false;
      description = "The given IBAN BBAN has not passed through country BBAN regular expression validation";
    } else if (!validateCheckDigitRegex(jsonClassification.getCheckDigitRegex(), checkDigit)) {
      isValid = false;
      description = "The given IBAN check digit has not passed through country check digit regular expression validation";
    } else if (!validateMod97(iban)) {
      isValid = false;
      description = "Given IBAN does not pass in the module 97 operation";
    }

    return IbanValidation.builder().iban(iban).isValid(isValid).description(description).jsonClassification(jsonClassification).build();
  }

  private boolean validateBbanRegex(String bbanRegex, String bban) {
    var re = Pattern.compile(bbanRegex);
    return re.matcher(bban).find();
  }

  private boolean validateCheckDigitRegex(String checkDigitRegex, String checkDigit) {
    var re = Pattern.compile(checkDigitRegex);
    return re.matcher(checkDigit).find();
  }

  private boolean validateMod97(String iban) {
    var mod97Input = iban.substring(4) + "" + iban.substring(0, 4);
    for (var k : charReplacementMap.keySet()) {
      mod97Input = mod97Input.replaceAll(String.valueOf(k), String.valueOf(charReplacementMap.get(k)));
    }
    var numericInput = new BigInteger(mod97Input);
    return numericInput.remainder(BigInteger.valueOf(97)).intValue() == 1;
  }

  private Map<Character, Integer> getCharReplacementMap() {
    Map<Character, Integer> replacement = new HashMap<>();
    replacement.put('A', 10);
    replacement.put('B', 11);
    replacement.put('C', 12);
    replacement.put('D', 13);
    replacement.put('E', 14);
    replacement.put('F', 15);
    replacement.put('G', 16);
    replacement.put('H', 17);
    replacement.put('I', 18);
    replacement.put('J', 19);
    replacement.put('K', 20);
    replacement.put('L', 21);
    replacement.put('M', 22);
    replacement.put('N', 23);
    replacement.put('O', 24);
    replacement.put('P', 25);
    replacement.put('Q', 26);
    replacement.put('R', 27);
    replacement.put('S', 28);
    replacement.put('T', 29);
    replacement.put('U', 30);
    replacement.put('V', 31);
    replacement.put('W', 32);
    replacement.put('X', 33);
    replacement.put('Y', 34);
    replacement.put('Z', 35);
    return replacement;
  }

}
