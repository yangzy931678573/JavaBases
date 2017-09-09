package com.yzy.enumAndSingleton;

enum EnumInstance {
    A("70-90"){
        @Override
        public String cvalue() {
            return "优";
        }
    };
    private String value;
    private EnumInstance(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract String cvalue();//最重要的是抽象方法，然后重写
}
