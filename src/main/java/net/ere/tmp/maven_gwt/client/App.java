package net.ere.tmp.maven_gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import net.ere.tmp.maven_gwt.shared.AppService;
import net.ere.tmp.maven_gwt.shared.AppServiceAsync;
import net.ere.tmp.maven_gwt.shared.BlogItem;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class App implements EntryPoint {

    String[] names = {"Alice", "Bob", "Charly", "Dora", "Emil", "Fjodor"};
    String[][] title = {{"JPA €", "JPA € ist gut .."},
            {"Spring", "Spring ist güt"},
            {"EJB", "Ist gut öüä ..."},
            {"GWT", "Wie man GWT richtig nutzt"},
            {"JSF", "Ist JSF schlecht?"},
            {"DB", "Welche DB ist die beste?"}
    };
    private Logger log = Logger.getLogger(App.class.getName());
    private AppServiceAsync appService = GWT.create(AppService.class);
    private CellTable<BlogItem> table = new CellTable<>();

    @Override
    public void onModuleLoad() {

        final Button button = new Button("Load data");
        final Button initButton = new Button("Init data");
        final Label labelLocalTime = new Label();
        final Label labelServerTime = new Label();
        labelLocalTime.setStyleName("myLabel");
        labelServerTime.setStyleName("myLabel");
        RootPanel.get("gwt").add(initButton);
        RootPanel.get("gwt").add(button);
        RootPanel.get("timeSpan1").add(labelLocalTime);
        RootPanel.get("timeSpan2").add(labelServerTime);

        button.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                log.info("Click");

                Date date = new Date();
                labelLocalTime.setText("Localtime:  " + format(date));

                appService.getTime(new AsyncCallback<Date>() {

                    @Override
                    public void onSuccess(Date result) {
                        log.info("Response: " + result);
                        labelServerTime.setText("Servertime: " + format(result));


                        appService.getAllBlogItem(new AsyncCallback<List<BlogItem>>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                log.warning("ERROR: " + caught.getMessage());
                            }

                            @Override
                            public void onSuccess(List<BlogItem> result) {
                                log.info("Load " + result.size() + " items");
                                table.setRowCount(result.size(), true);
                                table.setRowData(0, result);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        log.warning(caught.getMessage());
                    }
                });

            }
        });

        initButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initData();
            }
        });

        initTable();
    }

    private void initTable() {
        TextColumn<BlogItem> authorColumn = new TextColumn<BlogItem>() {
            @Override
            public String getValue(BlogItem blogItem) {
                return blogItem.getAuther();
            }
        };

        TextColumn<BlogItem> titleColumn = new TextColumn<BlogItem>() {
            @Override
            public String getValue(BlogItem blogItem) {
                return blogItem.getTitle();
            }
        };

        TextColumn<BlogItem> contentColumn = new TextColumn<BlogItem>() {
            @Override
            public String getValue(BlogItem blogItem) {
                return blogItem.getText();
            }
        };

        table.addColumn(authorColumn, "Name");
        table.addColumn(titleColumn, "Title");
        table.addColumn(contentColumn, "Content");

        RootPanel.get("gwt").add(table);
    }

    private void initData() {
        Random rd = new Random();

        for (int i = 0; i < 5; i++) {
            int a = rd.nextInt(5);
            int b = rd.nextInt(5);

            appService.createAuthor(names[a], title[b][0], title[b][1], new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                    log.warning(caught.getMessage());
                }

                @Override
                public void onSuccess(Void result) {
                    log.info("Data added");
                }

            });
        }
    }

    private String format(Date date) {
        return DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM).format(date);
    }

}
