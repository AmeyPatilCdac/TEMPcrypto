 package in.cdac.java.parameters;

public class PersonalFullAddress {
   private MatchingStrategy matchStrategy;
   private Integer matchValue;
   private String fullAddress;
   private Integer localMatchValue;
   private String localAddressValue;
   private String pidData = null;

   public MatchingStrategy getMatchStrategy() {
      return this.matchStrategy;
   }

   public void setMatchStrategy(MatchingStrategy matchStrategy) {
      this.matchStrategy = matchStrategy;
   }

   public Integer getMatchValue() {
      return this.matchValue;
   }

   public void setMatchValue(Integer matchValue) {
      this.matchValue = matchValue;
   }

   public String getFullAddress() {
      return this.fullAddress;
   }

   public void setFullAddress(String fullAddress) {
      this.fullAddress = fullAddress;
   }

   public Integer getLocalMatchValue() {
      return this.localMatchValue;
   }

   public void setLocalMatchValue(Integer localMatchValue) {
      this.localMatchValue = localMatchValue;
   }

   public String getLocalAddressValue() {
      return this.localAddressValue;
   }

   public void setLocalAddressValue(String localAddressValue) {
      this.localAddressValue = localAddressValue;
   }

   public String getPidData() {
      return this.pidData;
   }

   public void setPidData(String pidData) {
      this.pidData = pidData;
   }

   public PersonalFullAddress() {
      this.matchStrategy = MatchingStrategy.EXACT;
      this.matchValue = 100;
   }
}