package com.study.liking.models;

import br.com.integrati.sprinkles_inti_library.Model;
import br.com.integrati.sprinkles_inti_library.Query;
import br.com.integrati.sprinkles_inti_library.annotations.AutoIncrement;
import br.com.integrati.sprinkles_inti_library.annotations.Column;
import br.com.integrati.sprinkles_inti_library.annotations.Key;
import br.com.integrati.sprinkles_inti_library.annotations.Table;

@Table("own_user")
public class OwnUser extends Model {

    @Key
    @AutoIncrement
    @Column("id")
    public long id;

    @Column("name")
    public String name;

    @Column("last_name")
    public String lastName;

    @Column("login")
    public String login;

    @Column("email")
    public String email;

    @Column("save_credentials")
    public boolean saveCredentials;

    @Column("password")
    public String password;

    @Column("first_access")
    public boolean firstAccess = true;

    public static OwnUser getCredentials() {
        String sql = " SELECT * FROM own_user LIMIT 1";
        return Query.one(OwnUser.class, sql, true).get();
    }

    public boolean checkCredentials(OwnUser ownUser) {
        return this.login.equals(ownUser.login) && this.password.equals(ownUser.password);
    }

    public boolean isUserNotFilled() {
        return this.login == null || this.password == null;
    }
}
