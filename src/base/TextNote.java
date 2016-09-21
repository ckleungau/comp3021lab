package base;

public class TextNote extends Note {

	String content;
	
    public TextNote(String content) {
        super(content);
    }
    
    // Lab 3
    public String getContent() { return content; }
    
    public TextNote(String title, String content) {
        super(title);
        this.content = content;
    }

}
