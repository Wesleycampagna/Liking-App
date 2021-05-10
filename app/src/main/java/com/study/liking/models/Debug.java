package com.study.liking.models;

import br.com.integrati.sprinkles_inti_library.Model;
import br.com.integrati.sprinkles_inti_library.annotations.AutoIncrement;
import br.com.integrati.sprinkles_inti_library.annotations.Column;
import br.com.integrati.sprinkles_inti_library.annotations.Key;
import br.com.integrati.sprinkles_inti_library.annotations.Table;

@Table("debug")
public class Debug extends Model {

    @Key
    @AutoIncrement
    @Column("id")
    public long id;

    @Column("debug")
    public String debug;

    @Column("url")
    public String url;
}
