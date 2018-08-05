package introduction;

/**
 *
 * @author jherrera
 */
public class FrontendDetail {

    private final String title;
    private final String subTitle;
    private final String messages;

    public FrontendDetail(String title, String subTitle, String messages) {
        this.title = title;
        this.subTitle = subTitle;
        this.messages = messages;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getMessages() {
        return messages;
    }

}
