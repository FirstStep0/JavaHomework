import java.util.List;

public class News {
    private String title;
    private String text;

    public News() {this("", ""); }
    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public News(News news){
        this(news.title, news.text);
    }

    public void SetTitle(String title) {
        this.title = title;
    }

    public String GetTitle() {
        return title;
    }

    public void SetText(String text) {
        this.text = text;
    }

    public String GetText() {
        return text;
    }

    @Override
    public String toString() {
        return "Заголовок: " + title + "\n" +
                "Текст: " + text;
    }
}
