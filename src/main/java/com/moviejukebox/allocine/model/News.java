/*
 *      Copyright (c) 2004-2015 YAMJ Members
 *      http://code.google.com/p/moviejukebox/people/list
 *
 *      This file is part of the Yet Another Movie Jukebox (YAMJ).
 *
 *      The YAMJ is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      YAMJ is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with the YAMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 *      Web: http://code.google.com/p/moviejukebox/
 *
 */
package com.moviejukebox.allocine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;

public class News extends AbstractJsonUnknownHandleMapping {

    @JsonProperty("code")
    private String code;
    private String publication;
    @JsonProperty("title")
    private String title;
    private String displayMode;
    @JsonProperty("pageCount")
    private Integer pageCount;
    @JsonProperty("category")
    private List<CodeName> category;
    @JsonProperty("picture")
    private Picture picture;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPublication() {
        return publication;
    }

    @JsonSetter("publication")
    public void setPublication(AllocineDate publication) {
        this.publication = publication.getDateStart();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayMode() {
        return displayMode;
    }

    @JsonSetter("displayMode")
    public void setDisplayMode(CodeName displayMode) {
        this.displayMode = displayMode.getName();
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<CodeName> getCategory() {
        return category;
    }

    public void setCategory(List<CodeName> category) {
        this.category = category;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}