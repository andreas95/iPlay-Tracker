package main.java.com.andreas.tracker.iplay.model;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.TorrentsDB;
import main.java.com.andreas.tracker.iplay.utils.uriSchemeHandler.CouldNotOpenUriSchemeHandler;
import main.java.com.andreas.tracker.iplay.utils.uriSchemeHandler.URISchemeHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 7/14/16.
 */
public class Torrent {

    private String type;
    private String name;
    private String genre;
    private String tags;
    private String link1;
    private String link2;
    private int comments;
    private String added;
    private String size;
    private int snatched;
    private int seeds;
    private int leechs;
    private String description;
    private String thanks;
    private String uploader;
    private String filled;
    private String addedBy;
    private int votes;
    private final String URL_IMAGE_CATEGORIES="/main/resources/img/common/browse/table/categories/";
    private final String URL_IMAGE_TAGS="/main/resources/img/common/browse/table/tags/";
    private final String URL_IMAGE_LINKS="/main/resources/img/common/browse/table/links/";
    private final String URL_IMAGE_FILLED="/main/resources/img/common/button/";

    public Torrent() {}
    public Torrent(String type, String name, String genre, String tags, String link1, String link2, int comments, String added, String size, int snatched, int seeds, int leechs) {
        this.type=type;
        this.name=name;
        this.genre=genre;
        this.tags=tags;
        this.link1=link1;
        this.link2=link2;
        this.comments=comments;
        this.added=added;
        this.size=size;
        this.snatched=snatched;
        this.seeds=seeds;
        this.leechs=leechs;

    }

    //constructor for torrent details
    public Torrent(String type, String name, String genre, String tags, String link1, String link2, int comments, String added, String size, int snatched, int seeds, int leechs, String uploader, String description, String thanks ) {
        this.type=type;
        this.name=name;
        this.genre=genre;
        this.tags=tags;
        this.link1=link1;
        this.link2=link2;
        this.comments=comments;
        this.added=added;
        this.size=size;
        this.snatched=snatched;
        this.seeds=seeds;
        this.leechs=leechs;
        this.uploader=uploader;
        this.description=description;
        this.thanks=thanks;
    }

    //constructor for add torrent into database
    public Torrent(String type, String name, String genre, String tags, String link1, String link2, String description, String added, String size) {
        this.type=type;
        this.name=name;
        this.genre=genre;
        this.tags=tags;
        this.link1=link1;
        this.link2=link2;
        this.description=description;
        this.size=size;
        this.added=added;
    }

    //constructor for requests details
    public Torrent(String type, String name, int comments, String added, String addedBy, String filled, int votes) {
        this.type=type;
        this.name=name;
        this.comments=comments;
        this.added=added;
        this.addedBy=addedBy;
        this.filled=filled;
        this.votes=votes;
    }

    //constructor for add requests into database
    public Torrent(String type, String name, String genre, String description, String added, String addedBy) {
        this.type=type;
        this.name=name;
        this.genre=genre;
        this.description=description;
        this.added=added;
        this.addedBy=addedBy;
    }


    public String getUploader() {return uploader;}
    public String getFilled() {return filled;}
    public String getAddedBy() {return addedBy;}
    public String getType() {return type;}
    public String getName() {return name;}
    public String getGenre() {return genre;}
    public int getVotes() {return votes;}
    public String getTags() {return tags;}
    public String getLink1() {return  link1;}
    public String getLink2() {return link2;}
    public String getDescription() {return description;}
    public String getThanks() {return thanks;}
    public int getComments() {return comments;}
    public String getAdded() {return added;}
    public String getSize() {return size;}
    public int getSnatched() {return snatched;}
    public int getSeeds() {return seeds;}
    public int getLeechs() {return leechs;}
    public HBox getTypeImage() {
        HBox box=new HBox();
        box.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream(URL_IMAGE_CATEGORIES+type+".png"))));
        return box;
    }

    public HBox getFilledImage() {
        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        String img=filled.equals("yes")?"check":"delete";
        box.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream(URL_IMAGE_FILLED+img+".png"))));
        return box;
    }
    public HBox getTagsBox() {
        HBox box=new HBox(2);
        box.setAlignment(Pos.CENTER_RIGHT);
        if (!tags.equals("")) {
            String[] split_tags = tags.split(",");
            for (String tag : split_tags) {
                box.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream(URL_IMAGE_TAGS + tag + ".png"))));
            }
        }
        return box;
    }
    public HBox getLinksBox() {
        HBox box=new HBox(5);
        box.setAlignment(Pos.CENTER_RIGHT);
/*        Label direct=new Label("",new ImageView(new Image(getClass().getResourceAsStream(URL_IMAGE_LINKS + "link1.png"))));
        direct.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        direct.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        direct.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Downloader.directDownload(link1,name);
            }
        });*/
        Label link1_download=new Label("",new ImageView(new Image(getClass().getResourceAsStream(URL_IMAGE_LINKS + "link1.png"))));
        link1_download.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        link1_download.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        link1_download.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)  {
                URI magnetLinkUri = null;
                try {
                    magnetLinkUri = new URI(link1);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                URISchemeHandler uriSchemeHandler = new URISchemeHandler();
                try {
                    uriSchemeHandler.open(magnetLinkUri);
                } catch (CouldNotOpenUriSchemeHandler couldNotOpenUriSchemeHandler) {
                    couldNotOpenUriSchemeHandler.printStackTrace();
                }
            }
        });
        Label link2_download=new Label("",new ImageView(new Image(getClass().getResourceAsStream(URL_IMAGE_LINKS + "link2.png"))));
        link2_download.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        link2_download.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        link2_download.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)  {
                URI magnetLinkUri = null;
                try {
                    magnetLinkUri = new URI(link2);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                URISchemeHandler uriSchemeHandler = new URISchemeHandler();
                try {
                    uriSchemeHandler.open(magnetLinkUri);
                } catch (CouldNotOpenUriSchemeHandler couldNotOpenUriSchemeHandler) {
                    couldNotOpenUriSchemeHandler.printStackTrace();
                }
            }
        });
        box.getChildren().addAll(link1_download,link2_download);
        return box;
    }
    public VBox getNameBox() {
        VBox box=new VBox(2);
        box.setAlignment(Pos.CENTER_LEFT);
        Text top=new Text(name);
        top.setWrappingWidth(415);
        top.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        top.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        top.getStyleClass().add("link");
        top.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Shared.view_torrent= TorrentsDB.getTorrent(name);
                    Shared.thanks_this_torrent=TorrentsDB.thanksTorrent(name);
                    ScreenController.setScreen(ScreenController.Screen.VIEWTORRENT);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Text bottom=new Text("Genre: "+genre);
        bottom.setFill(Color.web("#6d6d6d"));
        bottom.setFont(Font.font(11));
        box.getChildren().addAll(top,bottom);
        return box;
    }
    public VBox getAddedBox() {
        VBox box=new VBox(2);
        box.setAlignment(Pos.CENTER);
        String[] split_date= added.split(" ");
        for (String dates : split_date) {
            Text date = new Text(dates);
            date.setFill(Color.WHITE);
            box.getChildren().add(date);
        }
        return box;
    }
    public VBox getSnatchedBox() {
        VBox box=new VBox(2);
        box.setAlignment(Pos.CENTER);
        Text snatch = new Text(Integer.toString(snatched));
        snatch.setFill(Color.WHITE);
        box.getChildren().add(snatch);
        Text times = new Text("times");
        times.setFill(Color.WHITE);
        box.getChildren().add(times);
        return box;
    }
    public VBox getSizeBox() {
        VBox box=new VBox(2);
        box.setAlignment(Pos.CENTER);
        String[] split_size= size.split(" ");
        for (String siz : split_size) {
            Text sizze = new Text(siz);
            sizze.setFill(Color.WHITE);
            box.getChildren().add(sizze);
        }
        return box;
    }

    public VBox getVotesBox() {
        VBox box=new VBox(2);
        box.setAlignment(Pos.CENTER);
        Text no_votes = new Text(Integer.toString(votes));
        no_votes.setFill(Color.WHITE);
        Text add_votes=new Text("[Add vote]");
        add_votes.getStyleClass().add("link");
        add_votes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TorrentsDB.addRequestVote(name);
                try {
                    ScreenController.setScreen(ScreenController.Screen.REQUESTS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        box.getChildren().addAll(no_votes,add_votes);
        return box;
    }

    public VBox getNameLink() {
        VBox box=new VBox();
        box.setAlignment(Pos.CENTER_LEFT);
        Text name_text=new Text(name);
        name_text.setWrappingWidth(415);
        name_text.getStyleClass().add("link");
        name_text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Shared.view_request=TorrentsDB.getRequest(name);
                try {
                    ScreenController.setScreen(ScreenController.Screen.VIEWREQUESTS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        box.getChildren().add(name_text);
        return box;
    }
}
