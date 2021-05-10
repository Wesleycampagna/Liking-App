package com.study.liking.models;

import android.os.Parcelable;

import com.study.liking.utils.MaskUtils;

import java.io.Serializable;
import java.util.List;

import br.com.integrati.sprinkles_inti_library.CursorList;
import br.com.integrati.sprinkles_inti_library.Model;
import br.com.integrati.sprinkles_inti_library.Query;
import br.com.integrati.sprinkles_inti_library.annotations.AutoIncrement;
import br.com.integrati.sprinkles_inti_library.annotations.Column;
import br.com.integrati.sprinkles_inti_library.annotations.Key;
import br.com.integrati.sprinkles_inti_library.annotations.Table;

@Table("user")
public class User extends Model implements Serializable {

    @Key
    @AutoIncrement
    @Column("id")
    public long id;

    @Column("name")
    public String name;

    @Column("last_name")
    public String lastName;

    @Column("email")
    public String email;

    @Column("phone_number")
    public String phoneNumber;

    @Column("birth_date")
    public String birthDate;

    @Column("cpf")
    public String cpf;

    @Column("avatar")
    public String avatar;

    public String concatName() {
        return this.name + " " + this.lastName;
    }

    public static boolean isValidUser(String cpf) {
        String sql = " SELECT * FROM user WHERE CPF = ? LIMIT 1";
        User user =  Query.one(User.class, sql, cpf).get();

        return user != null;
    }

    public static List<User> getAllUsers() {
        String sql = " SELECT * FROM user ";
        CursorList<User> cursorList = Query.many(User.class, sql, true).get();
        List<User> users = cursorList.asList();
        cursorList.close();
        return users;
    }

    public static List<User> filterUsersBy(String text) {
        StringBuilder sql = new StringBuilder()
            .append(" SELECT * FROM user ")
            .append(" WHERE name LIKE ? ")
            .append(" OR last_name LIKE ? ")
            .append(" OR email LIKE ? ")
            .append(" OR cpf LIKE ? ");

        text = String.format("%%%s%%", text.toLowerCase());

        CursorList<User> cursorList = Query.many(User.class, sql.toString(), text, text, text, text).get();
        List<User> users = cursorList.asList();
        cursorList.close();
        return users;
    }
}
