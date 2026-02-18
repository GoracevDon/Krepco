package com.example.krepco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireActivity extends AppCompatActivity {

    private LinearLayout linearLayoutQuestionnaire;
    private Button btnBack;
    private ImageButton btnQst1, btnQst2, btnQst3, btnQst4, btnQst5, btnQst6;
    private LinearLayout linearLayoutQuestions;
    private LinearLayout linearLayoutResult;
    private LinearLayout mainContainer;
    private Questionnaire currentQuestionnaire = null;
    private int currentQuestionIndex = 0;
    private List<Integer> answers = new ArrayList<>();
    private Button btnComplete;
    private Button btnReady;
    private Button btnOk;
    private List<Questionnaire> questionnaires = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        initViews();
        initQuestionnaires();
        setupListeners();
        showQuestionnaireSelection();
    }

    private void initViews() {
        linearLayoutQuestionnaire = findViewById(R.id.linearLayoutQuestionnaire);
        btnBack = findViewById(R.id.btnBack);
        btnQst1 = findViewById(R.id.btnQst1);
        btnQst2 = findViewById(R.id.btnQst2);
        btnQst3 = findViewById(R.id.btnQst3);
        btnQst4 = findViewById(R.id.btnQst4);
        btnQst5 = findViewById(R.id.btnQst5);
        btnQst6 = findViewById(R.id.btnQst6);

        mainContainer = findViewById(R.id.linearLayoutQuestionnaire);

        linearLayoutQuestions = new LinearLayout(this);
        linearLayoutQuestions.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutQuestions.setOrientation(LinearLayout.VERTICAL);
        linearLayoutQuestions.setVisibility(View.GONE);
        linearLayoutQuestions.setBackgroundColor(getResources().getColor(R.color.gray));

        linearLayoutResult = new LinearLayout(this);
        linearLayoutResult.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutResult.setOrientation(LinearLayout.VERTICAL);
        linearLayoutResult.setVisibility(View.GONE);
        linearLayoutResult.setBackgroundColor(getResources().getColor(R.color.gray));

        ((ViewGroup) mainContainer.getParent()).addView(linearLayoutQuestions);
        ((ViewGroup) mainContainer.getParent()).addView(linearLayoutResult);
    }

    private void initQuestionnaires() {
        Questionnaire q1 = new Questionnaire("Подбор крепежа");
        q1.addQuestion(new Question("Какой тип поверхности?", new String[]{"Бетон/Кирпич", "Гипсокартон", "Дерево"}, new int[]{0, 1, 2}));
        q1.addQuestion(new Question("Примерная нагрузка?", new String[]{"До 10 кг", "10-50 кг", "Более 50 кг"}, new int[]{0, 1, 2}));
        q1.addQuestion(new Question("Условия эксплуатации?", new String[]{"Сухие помещения", "Влажные помещения", "Улица"}, new int[]{0, 1, 2}));
        q1.setResultRules(new ResultRule[]{
                new ResultRule(new int[]{0, 0, 0}, "Дюбель-гвозди\n\nДля бетона и кирпича в сухих помещениях с нагрузкой до 10 кг используйте дюбель-гвозди 6x40 мм."),
                new ResultRule(new int[]{0, 1, 0}, "Рамные анкеры\n\nДля бетонных стен с нагрузкой 10-50 кг в сухих помещениях подойдут рамные анкеры 8x65 мм."),
                new ResultRule(new int[]{0, 2, 2}, "Химические анкеры\n\nДля высоких нагрузок на улице используйте химические анкеры с шпилькой M10."),
                new ResultRule(new int[]{1, 0, 0}, "Бабочки (молли)\n\nДля гипсокартона с нагрузкой до 10 кг используйте дюбель-бабочки."),
                new ResultRule(new int[]{1, 1, 1}, "Дюбель-зонтик\n\nДля влажных помещений и средних нагрузок на гипсокартон подойдут металлические дюбель-зонтики."),
                new ResultRule(new int[]{2, 2, 2}, "Саморезы по дереву\n\nДля деревянных поверхностей с высокими нагрузками используйте саморезы 8x100 мм."),
        });
        questionnaires.add(q1);

        Questionnaire q2 = new Questionnaire("Подбор утеплителя");
        q2.addQuestion(new Question("Что утепляем?", new String[]{"Стены", "Кровлю", "Пол"}, new int[]{0, 1, 2}));
        q2.addQuestion(new Question("Бюджет?", new String[]{"Эконом", "Стандарт", "Премиум"}, new int[]{0, 1, 2}));
        q2.addQuestion(new Question("Экологичность?", new String[]{"Не важно", "Желательно", "Обязательно"}, new int[]{0, 1, 2}));
        q2.setResultRules(new ResultRule[]{
                new ResultRule(new int[]{0, 0, 0}, "Минеральная вата\n\nДля стен с бюджетным вариантом подойдет минеральная вата средней плотности."),
                new ResultRule(new int[]{1, 1, 1}, "Базальтовая вата\n\nДля кровли со стандартным бюджетом выбирайте базальтовую вату повышенной плотности."),
                new ResultRule(new int[]{2, 2, 2}, "Эковата\n\nДля пола с требованиями к экологичности используйте эковату."),
                new ResultRule(new int[]{0, 2, 2}, "Пенополиуретан\n\nДля стен премиум-класса с максимальной экологичностью - напыляемый ППУ."),
        });
        questionnaires.add(q2);

        Questionnaire q3 = new Questionnaire("Подбор покрытия (пол)");
        q3.addQuestion(new Question("Тип помещения?", new String[]{"Жилая комната", "Кухня/Прихожая", "Ванная"}, new int[]{0, 1, 2}));
        q3.addQuestion(new Question("Теплый пол?", new String[]{"Нет", "Да", "Планируется"}, new int[]{0, 1, 2}));
        q3.addQuestion(new Question("Проходимость?", new String[]{"Низкая", "Средняя", "Высокая"}, new int[]{0, 1, 2}));
        q3.setResultRules(new ResultRule[]{
                new ResultRule(new int[]{0, 0, 0}, "Ламинат\n\nДля жилых комнат без теплого пола с низкой проходимостью подойдет ламинат 32 класса."),
                new ResultRule(new int[]{1, 1, 1}, "Керамогранит\n\nДля кухни с теплым полом и средней проходимостью выбирайте керамогранит."),
                new ResultRule(new int[]{2, 2, 2}, "Плитка\n\nДля ванной с высокой проходимостью используйте напольную плитку."),
                new ResultRule(new int[]{0, 1, 0}, "Паркетная доска\n\nДля жилых комнат с теплым полом подойдет паркетная доска."),
        });
        questionnaires.add(q3);

        Questionnaire q4 = new Questionnaire("Подбор покрытия (кровля)");
        q4.addQuestion(new Question("Угол наклона крыши?", new String[]{"Меньше 15°", "15-30°", "Больше 30°"}, new int[]{0, 1, 2}));
        q4.addQuestion(new Question("Бюджет?", new String[]{"Эконом", "Стандарт", "Премиум"}, new int[]{0, 1, 2}));
        q4.addQuestion(new Question("Сложность крыши?", new String[]{"Простая", "Средняя", "Сложная"}, new int[]{0, 1, 2}));
        q4.setResultRules(new ResultRule[]{
                new ResultRule(new int[]{0, 0, 0}, "Рулонные материалы\n\nДля пологих крыш с бюджетным вариантом подойдут наплавляемые материалы."),
                new ResultRule(new int[]{1, 1, 1}, "Металлочерепица\n\nДля средних углов наклона со стандартным бюджетом выбирайте металлочерепицу."),
                new ResultRule(new int[]{2, 2, 2}, "Гибкая черепица\n\nДля крутых скатов и сложных крыш премиум-класса подойдет гибкая черепица."),
                new ResultRule(new int[]{1, 2, 2}, "Керамическая черепица\n\nДля сложных крыш с премиум бюджетом - керамическая черепица."),
        });
        questionnaires.add(q4);

        Questionnaire q5 = new Questionnaire("Выбор герметика");
        q5.addQuestion(new Question("Где применяем?", new String[]{"Внутри помещения", "Снаружи", "В воде"}, new int[]{0, 1, 2}));
        q5.addQuestion(new Question("Что герметизируем?", new String[]{"Швы/стыки", "Сантехнику", "Окна/двери"}, new int[]{0, 1, 2}));
        q5.addQuestion(new Question("Нужна ли окраска?", new String[]{"Да", "Нет", "Не важно"}, new int[]{0, 1, 2}));
        q5.setResultRules(new ResultRule[]{
                new ResultRule(new int[]{0, 0, 0}, "Акриловый герметик\n\nДля внутренних швов с возможностью окраски подойдет акриловый герметик."),
                new ResultRule(new int[]{1, 1, 1}, "Силиконовый герметик\n\nДля наружных работ и сантехники выбирайте санитарный силикон."),
                new ResultRule(new int[]{2, 2, 2}, "Полиуретановый герметик\n\nДля окон и дверей с высокой эластичностью используйте полиуретановый герметик."),
                new ResultRule(new int[]{2, 1, 1}, "Тиоколовый герметик\n\nДля постоянного контакта с водой - тиоколовый герметик."),
        });
        questionnaires.add(q5);

        Questionnaire q6 = new Questionnaire("Выбор краски");
        q6.addQuestion(new Question("Что красим?", new String[]{"Стены/потолок", "Пол", "Мебель/декор"}, new int[]{0, 1, 2}));
        q6.addQuestion(new Question("Тип поверхности?", new String[]{"Минеральная", "Дерево", "Металл"}, new int[]{0, 1, 2}));
        q6.addQuestion(new Question("Эксплуатация?", new String[]{"Сухие помещения", "Влажные", "Наружные"}, new int[]{0, 1, 2}));
        q6.setResultRules(new ResultRule[]{
                new ResultRule(new int[]{0, 0, 0}, "Водоэмульсионная краска\n\nДля стен и потолков в сухих помещениях подойдет водоэмульсионная краска."),
                new ResultRule(new int[]{1, 1, 1}, "Акриловая эмаль\n\nДля деревянных полов во влажных помещениях выбирайте акриловую эмаль."),
                new ResultRule(new int[]{2, 2, 2}, "Молотковая эмаль\n\nДля металлической мебели и наружных работ - молотковая эмаль."),
                new ResultRule(new int[]{0, 2, 2}, "Алкидная эмаль\n\nДля металлических поверхностей снаружи помещений используйте алкидную эмаль."),
        });
        questionnaires.add(q6);
    }

    private void setupListeners() {
        btnQst1.setOnClickListener(v -> startQuestionnaire(0));
        btnQst2.setOnClickListener(v -> startQuestionnaire(1));
        btnQst3.setOnClickListener(v -> startQuestionnaire(2));
        btnQst4.setOnClickListener(v -> startQuestionnaire(3));
        btnQst5.setOnClickListener(v -> startQuestionnaire(4));
        btnQst6.setOnClickListener(v -> startQuestionnaire(5));

        btnBack.setOnClickListener(v -> {
            if (currentQuestionnaire != null) {
                cancelQuestionnaire();
            } else {
                Intent intent = new Intent(QuestionnaireActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startQuestionnaire(int index) {
        currentQuestionnaire = questionnaires.get(index);
        currentQuestionIndex = 0;
        answers.clear();

        linearLayoutQuestionnaire.setVisibility(View.GONE);

        btnBack.setText("Завершить опрос");

        showCurrentQuestion();
    }

    private void showCurrentQuestion() {
        linearLayoutQuestions.removeAllViews();
        linearLayoutQuestions.setVisibility(View.VISIBLE);
        linearLayoutResult.setVisibility(View.GONE);

        if (currentQuestionIndex < currentQuestionnaire.getQuestions().size()) {
            Question question = currentQuestionnaire.getQuestions().get(currentQuestionIndex);

            TextView tvQuestion = new TextView(this);
            tvQuestion.setText("Вопрос " + (currentQuestionIndex + 1) + ": " + question.getText());
            tvQuestion.setTextColor(getResources().getColor(R.color.white));
            tvQuestion.setTextSize(18);
            tvQuestion.setPadding(20, 30, 20, 20);
            linearLayoutQuestions.addView(tvQuestion);

            LinearLayout optionsLayout = new LinearLayout(this);
            optionsLayout.setOrientation(LinearLayout.VERTICAL);
            optionsLayout.setPadding(20, 0, 20, 20);

            String[] options = question.getOptions();
            int[] values = question.getValues();

            for (int i = 0; i < options.length; i++) {
                final int optionIndex = i;
                final int optionValue = values[i];

                Button btnOption = new Button(this);
                btnOption.setText(options[i]);
                btnOption.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        100));
                btnOption.setBackgroundColor(getResources().getColor(R.color.gray));
                btnOption.setTextColor(getResources().getColor(R.color.white));
                btnOption.setTextSize(16);
                btnOption.setPadding(20, 0, 20, 0);
                btnOption.setAllCaps(false);

                btnOption.setOnClickListener(v -> {
                    answers.add(optionValue);
                    currentQuestionIndex++;

                    if (currentQuestionIndex < currentQuestionnaire.getQuestions().size()) {
                        showCurrentQuestion();
                    } else {
                        showResult();
                    }
                });

                optionsLayout.addView(btnOption);

                if (i < options.length - 1) {
                    View spacer = new View(this);
                    spacer.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            10));
                    optionsLayout.addView(spacer);
                }
            }

            linearLayoutQuestions.addView(optionsLayout);

            if (btnReady == null) {
                btnReady = new Button(this);
                btnReady.setText("Готово");
                btnReady.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        100));
                btnReady.setBackgroundColor(getResources().getColor(R.color.orange));
                btnReady.setTextColor(getResources().getColor(R.color.white));
                btnReady.setTextSize(18);
                btnReady.setPadding(20, 0, 20, 0);
                btnReady.setOnClickListener(v -> {
                    if (answers.size() == currentQuestionnaire.getQuestions().size()) {
                        showResult();
                    } else {
                        Toast.makeText(this, "Ответьте на все вопросы", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            if (btnReady.getParent() != null) {
                ((ViewGroup) btnReady.getParent()).removeView(btnReady);
            }
            linearLayoutQuestions.addView(btnReady);
        }
    }

    private void showResult() {
        linearLayoutQuestions.setVisibility(View.GONE);
        linearLayoutResult.setVisibility(View.VISIBLE);
        linearLayoutResult.removeAllViews();

        String result = currentQuestionnaire.getResult(answers);

        TextView tvResultTitle = new TextView(this);
        tvResultTitle.setText("Результат подбора");
        tvResultTitle.setTextColor(getResources().getColor(R.color.orange));
        tvResultTitle.setTextSize(22);
        tvResultTitle.setPadding(20, 30, 20, 10);
        tvResultTitle.setGravity(android.view.Gravity.CENTER);
        linearLayoutResult.addView(tvResultTitle);

        View line = new View(this);
        line.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2));
        line.setBackgroundColor(getResources().getColor(R.color.orange));
        line.setPadding(20, 0, 20, 0);
        linearLayoutResult.addView(line);

        TextView tvResult = new TextView(this);
        tvResult.setText(result);
        tvResult.setTextColor(getResources().getColor(R.color.white));
        tvResult.setTextSize(16);
        tvResult.setPadding(30, 30, 30, 30);
        tvResult.setLineSpacing(5, 1);
        linearLayoutResult.addView(tvResult);

        if (btnOk == null) {
            btnOk = new Button(this);
            btnOk.setText("ОК");
            btnOk.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    100));
            btnOk.setBackgroundColor(getResources().getColor(R.color.orange));
            btnOk.setTextColor(getResources().getColor(R.color.white));
            btnOk.setTextSize(18);
            btnOk.setOnClickListener(v -> {
                backToSelection();
            });
        }

        if (btnOk.getParent() != null) {
            ((ViewGroup) btnOk.getParent()).removeView(btnOk);
        }
        linearLayoutResult.addView(btnOk);

        btnBack.setText("На главную");
    }

    private void cancelQuestionnaire() {
        currentQuestionnaire = null;
        currentQuestionIndex = 0;
        answers.clear();

        linearLayoutQuestions.setVisibility(View.GONE);
        linearLayoutResult.setVisibility(View.GONE);
        linearLayoutQuestionnaire.setVisibility(View.VISIBLE);

        btnBack.setText("На главную");
    }

    private void backToSelection() {
        currentQuestionnaire = null;
        currentQuestionIndex = 0;
        answers.clear();

        linearLayoutQuestions.setVisibility(View.GONE);
        linearLayoutResult.setVisibility(View.GONE);
        linearLayoutQuestionnaire.setVisibility(View.VISIBLE);

        btnBack.setText("На главную");
    }

    private void showQuestionnaireSelection() {
        linearLayoutQuestionnaire.setVisibility(View.VISIBLE);
        linearLayoutQuestions.setVisibility(View.GONE);
        linearLayoutResult.setVisibility(View.GONE);
    }

    class Questionnaire {
        private String title;
        private List<Question> questions = new ArrayList<>();
        private ResultRule[] resultRules;

        public Questionnaire(String title) {
            this.title = title;
        }

        public void addQuestion(Question q) {
            questions.add(q);
        }

        public void setResultRules(ResultRule[] rules) {
            this.resultRules = rules;
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public String getResult(List<Integer> userAnswers) {
            if (resultRules == null || resultRules.length == 0) {
                return "Рекомендация: Обратитесь к специалисту";
            }

            int[] answersArray = new int[userAnswers.size()];
            for (int i = 0; i < userAnswers.size(); i++) {
                answersArray[i] = userAnswers.get(i);
            }

            for (ResultRule rule : resultRules) {
                if (rule.matches(answersArray)) {
                    return rule.getResult();
                }
            }

            return "Рекомендация: Обратитесь к специалисту";
        }
    }

    class Question {
        private String text;
        private String[] options;
        private int[] values;

        public Question(String text, String[] options, int[] values) {
            this.text = text;
            this.options = options;
            this.values = values;
        }

        public String getText() {
            return text;
        }

        public String[] getOptions() {
            return options;
        }

        public int[] getValues() {
            return values;
        }
    }

    class ResultRule {
        private int[] answerPattern;
        private String result;

        public ResultRule(int[] answerPattern, String result) {
            this.answerPattern = answerPattern;
            this.result = result;
        }

        public boolean matches(int[] userAnswers) {
            if (userAnswers.length != answerPattern.length) {
                return false;
            }
            for (int i = 0; i < userAnswers.length; i++) {
                if (userAnswers[i] != answerPattern[i]) {
                    return false;
                }
            }
            return true;
        }

        public String getResult() {
            return result;
        }
    }
}