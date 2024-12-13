package com.example.tianshu.Enum;

public enum QuestionEnum {
    QUESTION_1("1", new Answer("http://www.hymop.cn/article-48-1.html", "string")),
    QUESTION_2("2", new Answer("images/Diagrams.jpg", "image")),
    QUESTION_3("3", new Answer("images/PetAwaken.jpg", "image")),
    QUESTION_4("4", new Answer("images/PetUnawakened.jpg", "image")),
    QUESTION_5("5", new Answer("images/Soaring.jpg", "image")),
    QUESTION_6("6", new Answer("images/Enchanting.jpg", "image")),
    QUESTION_7("7", new Answer("images/PetProject.jpg", "image")),
    QUESTION_8("8", new Answer("images/EquipmentCost.jpg", "image")),
    QUESTION_9("9", new Answer("images/Spirit.jpg", "image")),
    QUESTION_10("10", new Answer("images/Travel.jpg", "image")),
    QUESTION_11("11", new Answer("images/Evolution.jpg", "image")),
    QUESTION_12("12", new Answer("images/InnerAlchemy.jpg", "image")),
    QUESTION_13("13", new Answer("images/Refining.jpg", "image")),
    QUESTION_14("14", new Answer("images/Dust.jpg", "image")),
    QUESTION_15("15", new Answer("images/IllustratedHandbook.jpg", "image"));
    // 添加其他问题和答案的 URL...

    private final String question;
    private final Answer answer;

    QuestionEnum(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }
}
