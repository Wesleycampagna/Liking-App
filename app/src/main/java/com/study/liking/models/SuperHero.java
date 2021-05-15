package com.study.liking.models;

import java.util.List;

import br.com.integrati.sprinkles_inti_library.CursorList;
import br.com.integrati.sprinkles_inti_library.Model;
import br.com.integrati.sprinkles_inti_library.Query;
import br.com.integrati.sprinkles_inti_library.annotations.AutoIncrement;
import br.com.integrati.sprinkles_inti_library.annotations.Column;
import br.com.integrati.sprinkles_inti_library.annotations.Key;
import br.com.integrati.sprinkles_inti_library.annotations.Table;

@Table("super_hero")
public class SuperHero extends Model {

    @Key
    @AutoIncrement
    @Column("id")
    public long id;

    @Column("id_super_hero")
    public long idSuperHero;

    @Column("name")
    public String name;

    @Column("description")
    public String description;

    @Column("quantity_comics")
    public int quantityComics;

    @Column("url_image")
    public String urlImage;

    @Column("image_extension")
    public String imageExtension;

    public static List<SuperHero> findAll() {
        CursorList<SuperHero> cursorList = Query.many(SuperHero.class, " SELECT * FROM super_hero ", true).get();
        List<SuperHero> superHeroes = cursorList.asList();
        cursorList.close();
        return superHeroes;
    }

    public static List<SuperHero> filterSuperHeroesBy(String text) {
        StringBuilder sql = new StringBuilder()
                .append(" SELECT * FROM super_hero ")
                .append(" WHERE name LIKE ? ")
                .append(" OR id_super_hero LIKE ? ")
                .append(" OR description LIKE ? ");

        text = String.format("%%%s%%", text.toLowerCase());

        CursorList<SuperHero> cursorList = Query.many(SuperHero.class, sql.toString(), text, text, text).get();
        List<SuperHero> superHeroes = cursorList.asList();
        cursorList.close();
        return superHeroes;
    }
}
