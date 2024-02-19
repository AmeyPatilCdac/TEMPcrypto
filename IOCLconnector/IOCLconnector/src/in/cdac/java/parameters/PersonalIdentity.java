 package in.cdac.java.parameters;

public class PersonalIdentity {
   private MatchingStrategy matchStrategy = null;
   private int matchValue ;
   private String name = null;
   private Integer localMatchValue = null;
   private String nameInIndianLanguage = null;
   private GenderType gender = null;
   private String dateOfBirth = null;
   private DateOfBirthType dateOfBirthType = null;
   private Integer age = null;
   private String phone = null;
   private String email = null;
   private String pidData = null;

   public PersonalIdentity() {
      this.matchStrategy = MatchingStrategy.EXACT;
      this.matchValue = 100;
   }

   public MatchingStrategy getMatchStrategy() {
      return this.matchStrategy;
   }

   public void setMatchStrategy(MatchingStrategy matchStrategy) {
      this.matchStrategy = matchStrategy;
   }

   public int getMatchValue() {
      return this.matchValue;
   }

   public void setMatchValue(int matchValue) {
      this.matchValue = matchValue;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getLocalMatchValue() {
      return this.localMatchValue;
   }

   public void setLocalMatchValue(Integer localMatchValue) {
      this.localMatchValue = localMatchValue;
   }

   public GenderType getGender() {
      return this.gender;
   }

   public void setGender(GenderType gender) {
      this.gender = gender;
   }

   public String getDateOfBirth() {
      return this.dateOfBirth;
   }

   public void setDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public DateOfBirthType getDateOfBirthType() {
      return this.dateOfBirthType;
   }

   public void setDateOfBirthType(DateOfBirthType dateOfBirthType) {
      this.dateOfBirthType = dateOfBirthType;
   }

   public Integer getAge() {
      return this.age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNameInIndianLanguage() {
      return this.nameInIndianLanguage;
   }

   public void setNameInIndianLanguage(String nameInIndianLanguage) {
      this.nameInIndianLanguage = nameInIndianLanguage;
   }

   public String getPidData() {
      return this.pidData;
   }

   public void setPidData(String pidData) {
      this.pidData = pidData;
   }
}
    