package data;

import lombok.Getter;

@Getter
public class User {
    private String uid;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String email;
    private String fullName;
    private int dateOfBirth;

   private  User() {}

   public static Builder newBuilder() {
       return new User().new Builder();
   }

   public class Builder {
       private Builder() {}

       public Builder setUid(String uid) {
           User.this.uid = uid;
           return this;
       }
       public Builder setFirstName(String firstName) {
           User.this.firstName = firstName;
           return this;
       }
       public Builder setLastName(String lastName) {
           User.this.lastName = lastName;
           return this;
       }
       public Builder setGender(String gender) {
           User.this.gender = gender;
           return this;
       }
       public Builder setAge(int age) {
           User.this.age = age;
           return this;
       }
       public Builder setEmail(String email) {
           User.this.email = email;
           return this;
       }
       public Builder setFullName(String fullName) {
           User.this.fullName = fullName;
           return this;
       }
       public Builder setDateOfBirth(int dateOfBirth) {
           User.this.dateOfBirth = dateOfBirth;
           return this;
       }

       public User build() {
           return User.this;
       }
    }

}
