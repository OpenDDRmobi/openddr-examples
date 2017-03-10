/*
 * Copyright (c) 2011-2017 OpenDDR LLC and others. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package mobi.openddr.example.flow;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public @Model class HelloWorld
{
   private final String text = "Hello World!";

   private String letters;
   
   private String numbers;
   
   private String email;
   
   public HelloWorld() {}

   @PostConstruct
   public void initialize()
   {
      System.out.println(this.getClass().getSimpleName() + " was constructed");
   }

   public String getText()
   {
      return text;
   }

   @NotNull
   @NotEmpty
   @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters")
   public String getLetters()
   {
      return letters;
   }

   public void setLetters(String letters)
   {
      this.letters = letters;
   }

   @NotNull
   @NotEmpty
   @Digits(fraction = 0, integer = 2)
   public String getNumbers()
   {
      return numbers;
   }

   public void setNumbers(String numbers)
   {
      this.numbers = numbers;
   }

   @NotNull
   @NotEmpty
   @Email
   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

}
