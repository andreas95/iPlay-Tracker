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
import main.java.com.andreas.tracker.iplay.database.TorrentsDB;
import main.java.com.andreas.tracker.iplay.model.Torrent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 6/22/16.
 */
public class AdultFragment {

    @FXML
    private TextField FieldSearch;
    @FXML
    private ComboBox ComboSearch;
    @FXML
    private ComboBox ComboStatus;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private Button ButtonFilter;
    @FXML
    private VBox PaneSearch;
    @FXML
    private TableView TableTorrents;
    @FXML
    private TableColumn ColumnType;
    @FXML
    private TableColumn ColumnName;
    @FXML
    private TableColumn ColumnTags;
    @FXML
    private TableColumn ColumnLinks;
    @FXML
    private TableColumn ColumnComm;
    @FXML
    private TableColumn ColumnAdded;
    @FXML
    private TableColumn ColumnSize;
    @FXML
    private TableColumn ColumnSnatched;
    @FXML
    private TableColumn ColumnSeeds;
    @FXML
    private TableColumn ColumnLeechs;
    @FXML
    private HBox PaginationTop;
    @FXML
    private HBox PaginationBottom;
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
    private VBox PaneTotalTable;
    @FXML
    private VBox PaneMain;
    @FXML
    private Text TextTableEmpty;
    @FXML
    private Text TextSearch;
    private String search="";
    private String in="";
    private String status="";
    List<String> categories=new ArrayList<String>();

    public void initialize() {
        FieldSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.ENTER) {
                    filter();
                }
            }
        });

        PaneTotalTable.getChildren().remove(TextSearch);

        //initialize list of torrents
        createPagination();

        //disable reorderable
        ColumnType.impl_setReorderable(false);
        ColumnName.impl_setReorderable(false);
        ColumnTags.impl_setReorderable(false);
        ColumnLinks.impl_setReorderable(false);
        ColumnComm.impl_setReorderable(false);
        ColumnAdded.impl_setReorderable(false);
        ColumnSize.impl_setReorderable(false);
        ColumnSeeds.impl_setReorderable(false);
        ColumnLeechs.impl_setReorderable(false);
        ColumnSnatched.impl_setReorderable(false);

        //initialize table
        TableTorrents.setFixedCellSize(50);
        TableTorrents.prefHeightProperty().bind(Bindings.size(TableTorrents.getItems()).multiply(TableTorrents.getFixedCellSize()).add(50));
        ColumnType.setCellValueFactory(new PropertyValueFactory<Torrent, HBox>("typeImage"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<Torrent, String>("nameBox"));
        ColumnTags.setCellValueFactory(new PropertyValueFactory<Torrent, String>("tagsBox"));
        ColumnLinks.setCellValueFactory(new PropertyValueFactory<Torrent, String>("linksBox"));
        ColumnComm.setCellValueFactory(new PropertyValueFactory<Torrent, String>("comments"));
        ColumnComm.setStyle( "-fx-alignment: CENTER-RIGHT;");
        ColumnAdded.setCellValueFactory(new PropertyValueFactory<Torrent, String>("addedBox"));
        ColumnAdded.getStyleClass().add("column-added-style");
        ColumnSize.setCellValueFactory(new PropertyValueFactory<Torrent, String>("sizeBox"));
        ColumnSize.getStyleClass().add("column-center-style");
        ColumnSnatched.setCellValueFactory(new PropertyValueFactory<Torrent, String>("snatchedBox"));
        ColumnSnatched.getStyleClass().add("column-center-style");
        ColumnSeeds.setCellValueFactory(new PropertyValueFactory<Torrent, String>("seeds"));
        ColumnLeechs.setCellValueFactory(new PropertyValueFactory<Torrent, String>("leechs"));


        //initialize combobox
        ComboSearch.getItems().addAll("Name", "Description", "Genre");
        ComboSearch.setValue("Name");
        ComboStatus.getItems().addAll("All", "Active", "Dead");
        ComboStatus.setValue("All");

    }

    public void createPagination() {
        if (TorrentsDB.getTorrentsNo("Adult", search, in, status, categories)!=0) {
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
        total_torrents = TorrentsDB.getTorrentsNo("Adult", search,in,status,categories);
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
                TorrentsDB.getTorrentsList("Adult", pages.get(no_page),search,in,status,categories));
        search="";
        in="";
        status="";
        categories.clear();
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
    public void filter() {
        search=FieldSearch.getText();
        in=ComboSearch.getValue().toString();
        status=ComboStatus.getValue().toString();
        TextSearch.setText("Search results for \""+search+"\"");
        if (PaneTotalTable.getChildren().contains(TextSearch)) {
            PaneTotalTable.getChildren().remove(TextSearch);
        }
        PaneTotalTable.getChildren().add(0, TextSearch);
        createPagination();
    }

    @FXML
    public void update() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.ADULT);
    }
}
