package main.java.com.andreas.tracker.iplay.fragments;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.TorrentsDB;
import main.java.com.andreas.tracker.iplay.model.Torrent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 6/22/16.
 */
public class RequestsFragment {

    @FXML
    private ComboBox ComboCategories;
    @FXML
    private TextField FieldSearch;
    @FXML
    private TableView TableTorrents;
    @FXML
    private TableColumn ColumnType;
    @FXML
    private TableColumn ColumnName;
    @FXML
    private TableColumn ColumnComm;
    @FXML
    private TableColumn ColumnAdded;
    @FXML
    private TableColumn ColumnAddedBy;
    @FXML
    private TableColumn ColumnFilled;
    @FXML
    private TableColumn ColumnVotes;
    @FXML
    private VBox PaneMain;
    @FXML
    private VBox PaneTotalTable;
    @FXML
    private VBox PaneMakeRequest;
    @FXML
    private Text TextTableEmpty;
    @FXML
    private Text TextSearch;
    @FXML
    private Label ButtonPageNextTop;
    @FXML
    private Label ButtonPagePrevTop;
    @FXML
    private Label ButtonPageNextBottom;
    @FXML
    private Label ButtonPagePrevBottom;
    @FXML
    private HBox BoxPagesBottom;
    @FXML
    private HBox BoxPagesTop;
    @FXML
    private ComboBox ComboCategory;
    @FXML
    private TextField FieldName;
    @FXML
    private TextField FieldGenre;
    @FXML
    private TextArea TextDescription;
    @FXML
    private VBox PaneHeader;
    private String action;
    private String condition;
    private HashMap<String, String> map_categories=new HashMap<String, String>();
    private final String categories="Apple/Mac, Apps, Foreign, Games/PC, Games/PS2, Games/PS3, Games/PSP, Games/Wii," +
            " Games/X360, Linux/Unix, MSC, Mobile/Appz, Mobile/Movies, Movies/AVI.HD, Movies/AVI.HD.RO, Movies/BD," +
            " Movies/BD.RO, Movies/DVDR, Movies/DVDR.RO, Movies/HD, Movies/HD.RO, Movies/PSP, Movies/XVID, Sport," +
            " TV/Boxsets, TV/Episodes, TV/HD, Docs, Music, Adult";
    private final String categories_type="Apple, Apps, , Games.PC, Games.PS2, Games.PS3, Games.PSP, " +
            "Games.Wii, Games.X360, Unix, MISC, Mobile.Appz, Mobile.Movies, AVI.HD, AVI.HD.RO, BD, BD.RO, DVDR, " +
            "DVDR.RO, HD, HD.RO, PSP, XVID, Sport, TV.Boxsets, TV.Episodes, TV.HD, Docs, Music, Adult";

    public void initialize() {
        FieldSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.ENTER) {
                    search();
                }
            }
        });

        PaneMain.getChildren().remove(PaneMakeRequest);

        action="";
        condition="";

        PaneTotalTable.getChildren().remove(TextSearch);

        //create hash map categories
        String[] split_type = categories_type.split(", ");
        String[] split_category = categories.split(", ");
        for (int i = 0; i < split_type.length; i++) {
            map_categories.put(split_category[i], split_type[i]);
        }

        //initialize list of torrents
        createPagination();

        //disable reorderable
        ColumnType.impl_setReorderable(false);
        ColumnName.impl_setReorderable(false);
        ColumnAddedBy.impl_setReorderable(false);
        ColumnFilled.impl_setReorderable(false);
        ColumnComm.impl_setReorderable(false);
        ColumnAdded.impl_setReorderable(false);
        ColumnVotes.impl_setReorderable(false);

        //initialize table
        TableTorrents.setFixedCellSize(50);
        TableTorrents.prefHeightProperty().bind(Bindings.size(TableTorrents.getItems()).multiply(TableTorrents.getFixedCellSize()).add(50));
        ColumnType.setCellValueFactory(new PropertyValueFactory<Torrent, HBox>("typeImage"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<Torrent, String>("nameLink"));
        ColumnAddedBy.setCellValueFactory(new PropertyValueFactory<Torrent, String>("addedBy"));
        ColumnFilled.setCellValueFactory(new PropertyValueFactory<Torrent, HBox>("filledImage"));
        ColumnFilled.setStyle( "-fx-alignment: CENTER;");
        ColumnComm.setCellValueFactory(new PropertyValueFactory<Torrent, String>("comments"));
        ColumnComm.setStyle( "-fx-alignment: CENTER;");
        ColumnAdded.setCellValueFactory(new PropertyValueFactory<Torrent, String>("addedBox"));
        ColumnAdded.getStyleClass().add("column-added-style");
        ColumnVotes.setCellValueFactory(new PropertyValueFactory<Torrent, String>("votesBox"));
        ColumnVotes.setStyle( "-fx-alignment: CENTER;");


        ComboCategories.getItems().addAll("Apple/Mac","Apps","Foreign","Games/PC","Games/PS2","Games/PS3","Games/PSP","Games/Wii","" +
                "Games/X360","Linux/Unix","MSC","Mobile/Appz","Mobile/Movies","Movies/AVI.HD","Movies/AVI.HD.RO","Movies/BD","" +
                "Movies/BD.RO","Movies/DVDR","Movies/DVDR.RO","Movies/HD","Movies/HD.RO","Movies/PSP","Movies/XVID","Sport","" +
                "TV/Boxsets","TV/Episodes","TV/HD","Docs","Music","Adult");
        ComboCategory.getItems().addAll("Apple/Mac","Apps","Foreign","Games/PC","Games/PS2","Games/PS3","Games/PSP","Games/Wii","" +
                "Games/X360","Linux/Unix","MSC","Mobile/Appz","Mobile/Movies","Movies/AVI.HD","Movies/AVI.HD.RO","Movies/BD","" +
                "Movies/BD.RO","Movies/DVDR","Movies/DVDR.RO","Movies/HD","Movies/HD.RO","Movies/PSP","Movies/XVID","Sport","" +
                "TV/Boxsets","TV/Episodes","TV/HD","Docs","Music","Adult");

        FieldSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.ENTER) {
                    search();
                }
            }
        });
    }

    public void createPagination() {
        if (TorrentsDB.getRequestsNo(action,condition)!=0) {
            if (!PaneMain.getChildren().contains(PaneTotalTable)) {
                PaneMain.getChildren().add(PaneTotalTable);
            }
            PaneMain.getChildren().remove(TextTableEmpty);
            List<String> pages = createPageIndex();
            showPageNo(0, pages);
        } else {
            PaneMain.getChildren().remove(PaneTotalTable);
            if (!PaneMain.getChildren().contains(TextTableEmpty)) {
                PaneMain.getChildren().add(TextTableEmpty);
            }
        }
    }

    public List<String> createPageIndex() {
        List<String> list_page_index=new ArrayList<String>();
        int torrent_per_page=50;
        int first_page=1;
        int total_torrents= 0;
        total_torrents = TorrentsDB.getRequestsNo(action,condition);
        int no_pages=total_torrents/torrent_per_page;
        if (total_torrents % torrent_per_page != 0) {
            no_pages++;
        }
        for (int i=0; i<no_pages; i++) {
            if (no_pages==1) {
                list_page_index.add(Integer.toString(first_page) + " - " + Integer.toString(total_torrents));
            } else {
                list_page_index.add(Integer.toString(first_page) + " - " + Integer.toString(torrent_per_page));
                first_page=torrent_per_page+1;
                if (i==no_pages-2) {
                    torrent_per_page=total_torrents;
                } else {
                    torrent_per_page+=50;
                }
            }
        }
        return  list_page_index;
    }

    public void showPageNo(int no_page, List<String> pages) {
        //remove all pages for recursive
        BoxPagesTop.getChildren().clear();
        BoxPagesBottom.getChildren().clear();

        //initialize table
        ObservableList<Torrent> listTorrents= FXCollections.observableArrayList(
                TorrentsDB.getAllRequests(action,condition,pages.get(no_page)));
        TableTorrents.setItems(listTorrents);
        TableTorrents.setFixedCellSize(50);
        TableTorrents.prefHeightProperty().bind(Bindings.size(TableTorrents.getItems()).multiply(TableTorrents.getFixedCellSize()).add(50));
        //pointers
        //defaults
        ButtonPageNextTop.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonPageNextBottom.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonPagePrevTop.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonPagePrevBottom.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        if (no_page!=pages.size()-1) {
            ButtonPageNextTop.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
            ButtonPageNextTop.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
            ButtonPageNextBottom.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
            ButtonPageNextBottom.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));

            //event
            ButtonPageNextTop.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    showPageNo(no_page+1,pages);
                }
            });

            ButtonPageNextBottom.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    showPageNo(no_page+1,pages);
                }
            });
        }

        if (no_page!=0) {
            ButtonPagePrevTop.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
            ButtonPagePrevTop.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
            ButtonPagePrevBottom.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
            ButtonPagePrevBottom.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));

            //event
            ButtonPagePrevBottom.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    showPageNo(no_page-1,pages);
                }
            });

            ButtonPagePrevTop.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    showPageNo(no_page-1,pages);
                }
            });
        }


        //Dublicate Pane
        List<HBox> listOfBoxPages=new ArrayList<HBox>();
        listOfBoxPages.add(BoxPagesTop);
        listOfBoxPages.add(BoxPagesBottom);

        for (HBox BoxPages: listOfBoxPages) {
            if (pages.size() < 5) {
                List<Integer> indexs = new ArrayList<Integer>();
                if (pages.size() > 3) {
                    indexs.add(0);
                    indexs.add(1);
                    indexs.add(pages.size() - 2);
                    indexs.add(pages.size() - 1);
                } else {
                    for (int i = 0; i < pages.size(); i++) {
                        indexs.add(i);
                    }
                }
                for (int i : indexs) {
                    Text format_pages = new Text(pages.get(i));
                    int finalI = i;
                    format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            showPageNo(finalI, pages);
                        }
                    });
                    if (i == no_page) {
                        format_pages.getStyleClass().add("link");
                    } else {
                        format_pages.getStyleClass().add("inactive-link");
                    }
                    Text separator = new Text("|");
                    separator.getStyleClass().add("separator-text");
                    BoxPages.getChildren().add(format_pages);
                    if (i != pages.size() - 1) {
                        BoxPages.getChildren().add(separator);
                    }
                }
            } else {
                if (no_page == 0 || no_page == pages.size() - 1) {
                    List<Integer> indexs = new ArrayList<Integer>();
                    indexs.add(0);
                    indexs.add(1);
                    indexs.add(pages.size() - 2);
                    indexs.add(pages.size() - 1);
                    for (int i : indexs) {
                        Text format_pages = new Text(pages.get(i));
                        int finalI = i;
                        format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showPageNo(finalI, pages);
                            }
                        });
                        if (i == no_page) {
                            format_pages.getStyleClass().add("link");
                        } else {
                            format_pages.getStyleClass().add("inactive-link");
                        }
                        Text separator = new Text("|");
                        separator.getStyleClass().add("separator-text");
                        BoxPages.getChildren().add(format_pages);
                        if (i != pages.size() - 1) {
                            BoxPages.getChildren().add(separator);
                        }
                        if (i == 1) {
                            Text dots = new Text("...");
                            dots.getStyleClass().add("link");
                            Text separator2 = new Text("|");
                            separator2.getStyleClass().add("separator-text");
                            BoxPages.getChildren().addAll(dots, separator2);
                        }
                    }
                } else {

                    //show first two pages
                    for (int i = 0; i < 2; i++) {
                        Text format_pages = new Text(pages.get(i));
                        int finalI = i;
                        format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showPageNo(finalI, pages);
                            }
                        });
                        if (i == no_page) {
                            format_pages.getStyleClass().add("link");
                        } else {
                            format_pages.getStyleClass().add("inactive-link");
                        }
                        Text separator = new Text("|");
                        separator.getStyleClass().add("separator-text");
                        BoxPages.getChildren().addAll(format_pages, separator);
                    }


                    //generate middle pages
                    if (no_page == 1) {
                        Text format_pages = new Text(pages.get(no_page + 1));
                        format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showPageNo(no_page + 1, pages);
                            }
                        });
                        format_pages.getStyleClass().add("inactive-link");
                        Text separator = new Text("|");
                        separator.getStyleClass().add("separator-text");
                        BoxPages.getChildren().addAll(format_pages, separator);
                        if (pages.size() != 5) {
                            Text dots = new Text("...");
                            dots.getStyleClass().add("link");
                            Text separator2 = new Text("|");
                            separator2.getStyleClass().add("separator-text");
                            BoxPages.getChildren().addAll(dots, separator2);
                        }
                    } else if (no_page == pages.size() - 2) {
                        Text format_pages = new Text(pages.get(no_page - 1));
                        format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showPageNo(no_page - 1, pages);
                            }
                        });
                        format_pages.getStyleClass().add("inactive-link");
                        Text separator = new Text("|");
                        separator.getStyleClass().add("separator-text");

                        BoxPages.getChildren().addAll(format_pages, separator);
                        if (pages.size() != 5) {
                            Text dots = new Text("...");
                            dots.getStyleClass().add("link");
                            Text separator3 = new Text("|");
                            separator3.getStyleClass().add("separator-text");
                            BoxPages.getChildren().addAll(dots, separator3);
                        }
                    } else {
                        if (pages.size() == 5) {
                            Text separator = new Text("|");
                            separator.getStyleClass().add("separator-text");
                            Text format_pages = new Text(pages.get(no_page));
                            format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    showPageNo(no_page, pages);
                                }
                            });
                            format_pages.getStyleClass().add("link");
                            BoxPages.getChildren().addAll(format_pages, separator);
                        } else {
                            if (no_page != 2 && (pages.size() != 6 || no_page != pages.size() - 2) && !(no_page == pages.size() / 2 && (pages.size() == 6 || pages.size() == 7))
                                    && (no_page != 3 || pages.size() < 7)) {
                                Text dots = new Text("...");
                                dots.getStyleClass().add("link");
                                Text separator = new Text("|");
                                separator.getStyleClass().add("separator-text");
                                BoxPages.getChildren().addAll(dots, separator);
                            }

                            for (int i = no_page - 1; i < no_page + 2; i++) {
                                if (i != 1 && i != pages.size() - 2) {
                                    Text format_pages = new Text(pages.get(i));
                                    int finalI = i;
                                    format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            showPageNo(finalI, pages);
                                        }
                                    });
                                    if (i == no_page) {
                                        format_pages.getStyleClass().add("link");
                                    } else {
                                        format_pages.getStyleClass().add("inactive-link");
                                    }
                                    Text separa = new Text("|");
                                    separa.getStyleClass().add("separator-text");
                                    BoxPages.getChildren().addAll(format_pages, separa);
                                }
                            }
                            if (no_page != pages.size() - 2 && (pages.size() != 6 || no_page != 2) && !(no_page == pages.size() / 2 && (pages.size() == 6 || pages.size() == 7))
                                    && (no_page != pages.size() - 4 && no_page != pages.size() - 3 || pages.size() < 7)) {
                                Text dots2 = new Text("...");
                                dots2.getStyleClass().add("link");
                                Text separator2 = new Text("|");
                                separator2.getStyleClass().add("separator-text");
                                BoxPages.getChildren().addAll(dots2, separator2);
                            }
                        }
                    }


                    //show last two pages
                    for (int i = pages.size() - 2; i < pages.size(); i++) {
                        Text format_pages = new Text(pages.get(i));
                        int finalI = i;
                        format_pages.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                showPageNo(finalI, pages);
                            }
                        });
                        if (i == no_page) {
                            format_pages.getStyleClass().add("link");
                        } else {
                            format_pages.getStyleClass().add("inactive-link");
                        }
                        Text separator = new Text("|");
                        separator.getStyleClass().add("separator-text");
                        BoxPages.getChildren().add(format_pages);
                        if (i != pages.size() - 1) {
                            BoxPages.getChildren().add(separator);
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void view() {
        clear();
        if (!ComboCategories.getValue().equals("(Show All)")) {
            action = "view";
            condition = ComboCategories.getValue().toString();
            if (PaneTotalTable.getChildren().contains(TextSearch)) {
                PaneTotalTable.getChildren().remove(TextSearch);
            }
            createPagination();
        }
    }

    @FXML
    private void search() {
        clear();
        action="search";
        condition=FieldSearch.getText();
        TextSearch.setText("Search results for \""+condition+"\"");
        if (PaneTotalTable.getChildren().contains(TextSearch)) {
            PaneTotalTable.getChildren().remove(TextSearch);
        }
        PaneTotalTable.getChildren().add(0, TextSearch);
        createPagination();
    }

    @FXML
    private void select_filled() {
        clear();
        action="filled";
        condition="";
        if (PaneTotalTable.getChildren().contains(TextSearch)) {
            PaneTotalTable.getChildren().remove(TextSearch);
        }
        createPagination();
    }

    @FXML
    private void make_request() {
        PaneMain.getChildren().removeAll(PaneHeader,PaneTotalTable);
        if (PaneMain.getChildren().contains(TextTableEmpty)) {
            PaneMain.getChildren().remove(TextTableEmpty);
        }
        PaneMain.getChildren().add(PaneMakeRequest);
    }

    @FXML
    private void my_requests() {
        clear();
        action="myrequests";
        condition="";
        if (PaneTotalTable.getChildren().contains(TextSearch)) {
            PaneTotalTable.getChildren().remove(TextSearch);
        }
        createPagination();
    }

    @FXML
    private void cancel() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.REQUESTS);
    }

    @FXML
    private void save_request() throws IOException {
        if (ComboCategory.getValue().equals("None") || FieldName.getText().isEmpty() || TextDescription.getText().isEmpty()) {
            Shared.message_type = "error";
            Shared.message_text = "Don't leave field blank.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            Date data = new Date();
            String date_format = String.format("%tF %<tT", data);
            if (
                    TorrentsDB.addRequest(new Torrent(map_categories.get(ComboCategory.getValue().toString()), FieldName.getText(),
                            FieldGenre.getText(), "<html>\n" +
                            "<body style='background-color: #191919;' contenteditable='false'>\n" +
                            "<font face='Segoe UI' color='#ffffff'>"+TextDescription.getText().toString()+"</font>\n</body>\n</html>"
                            , date_format, Shared.user))) {
                Shared.message_type = "accept";
                Shared.message_text = "The request are saved.";
                ScreenController.setScreen(ScreenController.Screen.MESSAGE);
            } else {
                Shared.message_type = "warn";
                Shared.message_text = "The request already exists.";
                ScreenController.setScreen(ScreenController.Screen.MESSAGE);
            }
        }
    }

    private void clear() {
        TextSearch.setText("");
        ComboCategories.setValue("(Show All)");
    }
}
