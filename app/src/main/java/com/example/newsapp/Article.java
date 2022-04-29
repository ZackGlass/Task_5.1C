package com.example.newsapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    private int position, image;
    private String source, headline, description, topic;
    private boolean top;

    public Article(int position, int image, String source, String headline, String description, String topic, boolean top) {
        this.position = position;
        this.image = image;
        this.source = source;
        this.headline = headline;
        this.description = description;
        this.topic = topic;
        this.top = top;
    }

    protected Article(Parcel in) {
        position = in.readInt();
        image = in.readInt();
        source = in.readString();
        headline = in.readString();
        description = in.readString();
        topic = in.readString();
        top = in.readByte() != 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(position);
        parcel.writeInt(image);
        parcel.writeString(source);
        parcel.writeString(headline);
        parcel.writeString(description);
        parcel.writeString(topic);
        parcel.writeByte((byte) (top ? 1 : 0));
    }
}
