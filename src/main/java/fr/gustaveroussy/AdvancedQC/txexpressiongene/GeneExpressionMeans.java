package fr.gustaveroussy.AdvancedQC.txexpressiongene;

public class GeneExpressionMeans {


	public class GeneExpressionMean  {
		String geneName;
		Double meanExpression;
		public GeneExpressionMean (String geneName, Double meanExpression) {
			super();
			this.geneName = geneName;
			this.meanExpression = meanExpression;
		}
		public String getgeneName() {
			return geneName;
		}
		public Double getmeanExpression() {
			return meanExpression;
		}
		@Override
		public String toString() {
			return this.geneName + ": " + this.meanExpression +  " " ;
		}
		
	}


}
