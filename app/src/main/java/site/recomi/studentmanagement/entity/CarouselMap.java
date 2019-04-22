package site.recomi.studentmanagement.entity;

public class CarouselMap {
    private String img_url;         //图片地址
    private String article_url;     //文章地址

    public CarouselMap(String img_url, String article_url) {
        this.img_url = img_url;
        this.article_url = article_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }
}
