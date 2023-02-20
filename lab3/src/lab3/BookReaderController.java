package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;
import javafx.scene.layout.*;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();

		Set<String> set = new HashSet<String>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			set.add(word);
		}
		GeneralWordCounter gwc = new GeneralWordCounter(set);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			gwc.process(word);
		}
		scan.close();
		s.close();

		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(gwc.getWords());
		ListView<Map.Entry<String, Integer>> listView = new ListView<>(words);
		root.setCenter(listView);

		Button abc = new Button("Alphabetic");
		Button nbr = new Button("Frequency");
		HBox hbox = new HBox(abc, nbr);
		root.setBottom(hbox);

		abc.setOnAction(event -> {
			words.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		});
		nbr.setOnAction(event -> {
			words.sort((e1, e2) -> e2.getValue() - e1.getValue());
		});
		TextField tf = new TextField();
		Button search = new Button("Search");
		search.setDefaultButton(true);
		hbox.getChildren().addAll(tf, search);
		search.setOnAction(event -> {
			// if (words.contains(tf.getCharacters())) {
			for (Map.Entry<String, Integer> i : words) {
				if(i.getKey().equals(tf.getText().toLowerCase().replaceAll("\\s",""))){
					listView.scrollTo(i);
					listView.getSelectionModel().select(i);
				}
			}
			// }
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}