package jp.ac.dendai.im.cps.proximitysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SubActivity extends AppCompatActivity {

    private final List<Pair<Integer, String>> FORTUNE_TELLINGS = Arrays.asList(
            new Pair<>(R.drawable.money, "素晴らしい１日になりそう"),
            new Pair<>(R.drawable.bath, "まあまあ良い１日になりそう"),
            new Pair<>(R.drawable.lunch, "健康的な食事が幸福のカギ"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.txtFortune);

        // FORTUNE_TELLINGSからランダムで選ぶ
        Pair<Integer, String> fortune = FORTUNE_TELLINGS.get(new Random().nextInt(FORTUNE_TELLINGS.size()));

        imageView.setImageDrawable(getResources().getDrawable(fortune.first));
        textView.setText(fortune.second);
    }
}
