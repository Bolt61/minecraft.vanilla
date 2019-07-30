package ch.bolt61.vanillaserver.locales;

public enum LanguageEnum {

	GERMAN("German", "de"),
	ENGLISH("English", "en");
	
  private String label;
	private String code;
	
	private LanguageEnum(String label, String code) {
	  this.label = label;
		this.code = code;
	}
	
	public String getLabel() {
    return label;
  }
	
	public String getCode() {
		return code;
	}
	
	public LanguageEnum getFromCode(String code) {
		for(LanguageEnum l : LanguageEnum.values()) {
			if(l.getCode().equalsIgnoreCase(code)) {
				return l;
			}
		}
		return null;
	}
}
