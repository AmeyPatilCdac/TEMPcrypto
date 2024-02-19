  package in.cdac.java.parameters;

public class PersonalAddress extends Address {
   private MatchingStrategy matchStrategy;
   private String pidData = null;

   public PersonalAddress() {
      this.matchStrategy = MatchingStrategy.EXACT;
   }

   public MatchingStrategy getMatchStrategy() {
      return this.matchStrategy;
   }

   public void setMatchStrategy(MatchingStrategy matchStrategy) {
      this.matchStrategy = matchStrategy;
   }

   public String getPidData() {
      return this.pidData;
   }

   public void setPidData(String pidData) {
      this.pidData = pidData;
   }
}