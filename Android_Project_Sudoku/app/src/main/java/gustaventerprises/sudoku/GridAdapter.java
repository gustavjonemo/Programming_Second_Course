package gustaventerprises.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.TextView.BufferType.NORMAL;

public class GridAdapter extends BaseAdapter {
    private EditText t[][] = new EditText[9][9];
    private Board b;
    private Context context;

    /**Skapar en adapter som fungerar som brygga mellan spel och gränssnitt.
     * Sätter även inmatningstyp för spelet (en siffra) och bakgrundsfärg.
     *
     * @param context används vid skapande av textrutorna så att dom skapas på rätt ställe*/
    public GridAdapter(Context context) {
        this.context = context;
        for (int i2 = 0; i2 < 9; i2++) {
            for (int i1 = 0; i1 < 9; i1++) {
                t[i1][i2] = new EditText(context);
                t[i1][i2].setInputType(InputType.TYPE_CLASS_NUMBER);
                t[i1][i2].setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                t[i1][i2].setGravity(Gravity.CENTER_HORIZONTAL);
                if(i1 < 3 || i1 > 5){
                    if(i2 < 3 || i2 > 5) {
                        t[i1][i2].setBackgroundColor(Color.rgb(205, 205, 205));
                    } else {
                        t[i1][i2].setBackgroundColor(Color.rgb(230, 230, 230));
                    }
                } else {
                    if(i2 < 3 || i2 > 5) {
                        t[i1][i2].setBackgroundColor(Color.rgb(230, 230, 230));
                    } else {
                        t[i1][i2].setBackgroundColor(Color.rgb(205, 205, 205));
                    }
                }
            }
        }
        b = new Board();
    }

    /**Hämtar antalet rutor på brädet.
     *
     * @return  antalet rutor på brädet, vilket är 81*/
    @Override
    public int getCount() {
        return 81;
    }

    /**Hämtar det föremål som finns på en given position. Används ej.
     *
     * @return  null då metoden ej används*/
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**Hämtar Idt av det föremål som finns på en given position. Används ej.
     *
     * @return  0 då metoden ej används*/
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**Hämtar den textruta som finns på en given position.
     *
     * @param position linjära positionen som textrutan skall hämtas från
     * @return         textrutan på den givna positionen*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int x = position / 9;
        int y = position % 9;
        return t[x][y];
    }

    /**Löser brädet och uppdaterar gränssnittet med lösningen.
     * Om ingen lösning finns meddelas även detta.*/
    public void solve() {
        for (int i2 = 0; i2 < 9; i2++) {
            for (int i1 = 0; i1 < 9; i1++) {
                b.remove(i2, i1);
            }
        }
        for (int i2 = 0; i2 < 9; i2++) {
            for (int i1 = 0; i1 < 9; i1++) {
                String value = t[i1][i2].getText().toString();
                int finalValue;
                if (value.equals("")) {
                    finalValue = 0;
                } else {
                    finalValue = Integer.parseInt(value);
                }
                if (finalValue < 10 && finalValue > 0) {
                    b.add(finalValue, i1, i2);
                }
            }
        }
        if (b.solve()) {
            for (int i2 = 0; i2 < 9; i2++) {
                for (int i1 = 0; i1 < 9; i1++) {
                    t[i1][i2].setText(Integer.toString(b.get(i1, i2)), NORMAL);
                }
            }
        } else {
            Toast toast = Toast.makeText(context, "Lösning saknas", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    /**Brädet nollställs och gränssnittet töms.*/
    public void clear() {
        for (int i2 = 0; i2 < 9; i2++) {
            for (int i1 = 0; i1 < 9; i1++) {
                b.remove(i1, i2);
                t[i1][i2].setText("");
            }
        }
    }
}
