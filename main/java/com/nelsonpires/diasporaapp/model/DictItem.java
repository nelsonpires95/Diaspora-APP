package com.nelsonpires.diasporaapp.model;

public class DictItem {

    private String name;
    private String Description;
    private String rating;
    private int nb_episode;
    private String category;
    private String adress;
    private String image;

    public DictItem() {
    }

    public DictItem(String name, String description, String rating, int nb_episode, String category, String adress, String image) {
        this.name = name;
        Description = description;
        this.rating = rating;
        this.nb_episode = nb_episode;
        this.category = category;
        this.adress = adress;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNb_episode() {
        return nb_episode;
    }

    public void setNb_episode(int nb_episode) {
        this.nb_episode = nb_episode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String studio) {
        this.adress = studio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
