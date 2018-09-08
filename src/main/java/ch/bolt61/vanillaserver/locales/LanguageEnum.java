package ch.bolt61.vanillaserver.locales;

public enum LanguageEnum {

	GERMAN("de"),
	ENGLISH("en");
	
	private String code;
	
	private LanguageEnum(String code) {
		this.code = code;
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
