package view;

import beans.Contact;
import beans.FieldType;
import beans.Gender;
import beans.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserInput {
    private final Scanner scanner = new Scanner(System.in);
    private String validateInput(String placeholder, List<Rule> rules) {
        System.out.println(placeholder + ":");
        while (true) {
            String input = scanner.nextLine();
            int length = rules.size();
            int i;
            for (i = 0; i < length; i++) {
                Rule rule = rules.get(i);
                boolean pass = rule.validate(input);
                if (!pass) {
                    System.out.println(rule.getMessage());
                    break;
                }
            }
            if (i == length) {
                return input;
            }
        }
    }
    public String getSearchKeyword() {
        return validateInput("请输入关键字进行搜索", new ArrayList<Rule>() {{
            add(new Rule.Builder(true, true).setMessage("请输入关键字").build());
            add(new Rule.Builder().setMax(20).setMessage("最多允许20个字符").build());
        }});
    }
    public MenuItem getSelectedMenuItem(List<MenuItem> menuItemList) {
        List<String> validMenuItemKeys = menuItemList.stream().map(MenuItem::getKey).collect(Collectors.toList());
        String selectedMenuItemKey = validateInput("请输入菜单项对应的数字", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入>").required().build());
            add(new Rule.Builder("<请输入有效的菜单项>").setValidator(validMenuItemKeys::contains).build());
        }});
        return menuItemList.stream().filter(item -> item.getKey().equals(selectedMenuItemKey)).collect(Collectors.toList()).get(0);
    }
    public Contact getSelectedContact(List<Contact> contactList) {
        int[] validContactIds = contactList.stream().map(Contact::getId).mapToInt(Integer::intValue).toArray();
        String validInput = validateInput("请输入联系人ID", new ArrayList<Rule>() {{
            add(new Rule.Builder().required().build());
            add(new Rule.Builder().whitespace().setMessage("不允许空字符").build());
            add(new Rule.Builder().setFieldType(FieldType.NUMBER).setMessage("请输入数字").build());
            add(new Rule.Builder().setValidator(value -> {
                Arrays.sort(validContactIds);
                return Arrays.binarySearch(validContactIds, Integer.parseInt(value)) >= 0;
            }).build());
        }});
        int contactId = Integer.parseInt(validInput);
        return contactList.stream().filter(contact -> contact.getId() == contactId).collect(Collectors.toList()).get(0);
    }
    public String getName() {
        return validateInput("请输入联系人姓名(20个字符以内)", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入姓名>").required().build());
            add(new Rule.Builder("<最多支持20个字符>").setMax(20).build());
        }});
    }
    public String getAliasName() {
        return validateInput("请输入联系人别名(40个字符以内)", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入姓名>").whitespace().build());
            add(new Rule.Builder("<最多支持40个字符>").setMax(40).build());
        }});
    }
    public String getTelephone() {
        return validateInput("请输入联系人手机号", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入手机号>").required().build());
            add(new Rule.Builder("<输入的手机号不合法>").setPattern(Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$")).build());
        }});
    }
    public int getAge() {
        String validatedInput = validateInput("请输入联系人年龄", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入数字>").setFieldType(FieldType.NUMBER).setMin(1).setMax(120).build());
        }});
        return Integer.parseInt(validatedInput);
    }
    public Gender getGender() {
        final String MALE_FLAG = "男";
        final String FEMALE_FLAG = "女";
        String validatedInput = validateInput("请输入联系人性别 (男 | 女)", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入数字>").setValidator(
                    val -> {
                        if (val != null && val.length() > 0) {
                            return MALE_FLAG.equals(val) || FEMALE_FLAG.equals(val);
                        }
                        return true;
                    }
            ).build());
        }});
        return Enum.valueOf(Gender.class, MALE_FLAG.equals(validatedInput) ? "MALE" : "FEMALE");
    }

    public boolean isConfirm(String hint) {
        String validatedInput = validateInput(hint + "(是：Y y | 否：F f)", new ArrayList<Rule>() {{
            add(new Rule.Builder("<请输入(Y y | F f)>").setValidator(
                    val -> {
                        if (val != null && val.length() > 0) {
                            return val.equalsIgnoreCase("f") || val.equalsIgnoreCase("y");
                        }
                        return false;
                    }
            ).build());
        }});
        return validatedInput.equalsIgnoreCase("y");
    }
}
