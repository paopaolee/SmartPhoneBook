package beans;

import utils.RegexUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule implements  Validator<String> {
    private final String message;
    private final boolean required;
    private final FieldType type;
    private final Pattern pattern;
    private final int max;
    private final int min;
    private final boolean whitespace;
    private final Validator<String> validator;
   private Rule(Builder builder) {
       this.message = builder.message;
       this.required = builder.required;
       this.pattern = builder.pattern;
       this.max = builder.max;
       this.min = builder.min;
       this.type = builder.type;
       this.whitespace = builder.whitespace;
       if (builder.validator != null) {
           this.validator = builder.validator;
       } else if (pattern != null) {
           this.validator =  val -> pattern.matcher(val).matches();
       } else  if (type == FieldType.EMAIL) {
           this.validator = RegexUtils.getEmailValidator();
       } else if (type == FieldType.URL) {
           this.validator = RegexUtils.getUrlValidator();
       } else  if (type == FieldType.NUMBER) {
           this.validator = val -> {
               Matcher matcher = Pattern.compile(RegexUtils.NUMBER_REGEX).matcher(val);
               boolean isNumber = matcher.matches();
               if (isNumber) {
                   int number = Integer.parseInt(val);
                   return  number >= this.min && number <= this.max;
               }
               return false;
           };
       } else  {
           this.validator = val -> {
               int length = val.length();
               return length >= this.min && length <= this.max;
           };
       }
   }


   public String getMessage() {
       return this.message;
   }

    @Override
    public boolean validate(String val) {
        if (this.required && val == null ) {
            return false;
        }
        if (this.whitespace && (val == null || val.length() == 0)) {
            return false;
        }
        return this.validator.validate(val);
    }


    public static class Builder {
        private String message;
        private boolean required;
        private FieldType type;
        private Pattern pattern;
        private int max;
        private int min;
        private boolean whitespace;
        private Validator<String> validator;
        public Builder(boolean required, boolean whitespace) {
            this();
            this.required = required;
            this.whitespace = whitespace;
        }
        public Builder(String message) {
            this();
            this.message = message;
        }

        public Builder() {
            this.type = FieldType.STRING;
            this.max = Integer.MAX_VALUE;
            this.min = 0;
        }

        public Builder required() {
            this.required = true;
            return this;
        }

        public Builder whitespace() {
            this.whitespace = true;
            return this;
        }


        public Builder setFieldType(FieldType type) {
            if (type == FieldType.NUMBER) {
                this.min = Integer.MIN_VALUE;
            }
            this.type = type;
            return this;
        }

        public Builder setPattern(Pattern pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder setMax(int max) {
            this.max = max;
            return this;
        }

        public Builder setMin(int min) {
            this.min = min;
            return this;
        }

        public Builder setValidator(Validator<String> validator) {
            this.validator = validator;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Rule build() {
            return new Rule(this);
        }
    }
}
