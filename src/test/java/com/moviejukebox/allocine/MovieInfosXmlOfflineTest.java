/*
 *      Copyright (c) 2004-2014 YAMJ Members
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
package com.moviejukebox.allocine;

import com.moviejukebox.allocine.jaxb.Release;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yves Blusseau
 *
 */
public class MovieInfosXmlOfflineTest {

    File       avatarFile   = null;
    File       sample1File  = null;
    File       sample2File  = null;
    MovieInfos avatarInfos  = null;
    MovieInfos sample1Infos = null;
    MovieInfos sample2Infos = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        avatarFile   = new File("src/test/resources/avatar.xml");
        sample1File  = new File("src/test/resources/sample1.xml");
        sample2File  = new File("src/test/resources/sample2.xml");
        avatarInfos  = XMLAllocineAPIHelper.getMovieInfos(avatarFile);
        sample1Infos = XMLAllocineAPIHelper.getMovieInfos(sample1File);
        sample2Infos = XMLAllocineAPIHelper.getMovieInfos(sample2File);
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.XMLAllocineAPIHelper#getMovieInfos()}.
     */
    @Test
    public void testGetMovieInfos() throws Exception {
        File noresultFile = new File("src/test/resources/noresult.xml");
        MovieInfos noresultInfos = XMLAllocineAPIHelper.getMovieInfos(noresultFile);
        Assert.assertEquals(false, noresultInfos.isValid());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.MovieInfos#getSynopsis()}.
     */
    @Test
    public void testGetSynopsis() {
        String validStart = "Malgré sa paralysie, Jake Sully";
        String validEnd   = "sauve la vie de Jake...";

        String avatarSynopsis = avatarInfos.getSynopsis();
        Assert.assertEquals(993, avatarSynopsis.length());
        Assert.assertTrue(avatarSynopsis.startsWith(validStart));
        Assert.assertTrue(avatarSynopsis.endsWith(validEnd));

        String sample1Synopsis = sample1Infos.getSynopsis();
        Assert.assertEquals("Un synopsis html avec des sauts de lignes et de multiples espaces...", sample1Synopsis);

        String sample2Synopsis = sample2Infos.getSynopsis();
        Assert.assertEquals("", sample2Synopsis);

    }

    /**
     * Test method for {@link com.moviejukebox.allocine.MovieInfos#getRating()}.
     */
    @Test
    public void testGetRating() {
        Assert.assertEquals(83, avatarInfos.getRating());
        Assert.assertEquals(-1, sample1Infos.getRating());
        Assert.assertEquals(-1, sample2Infos.getRating());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.MovieInfos#getCertification()}.
     */
    @Test
    public void testGetCertification() {
        Assert.assertEquals("All", avatarInfos.getCertification());
        Assert.assertEquals("12", sample1Infos.getCertification());
        Assert.assertEquals("All", sample2Infos.getCertification());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.MovieInfos#getActors()}.
     */
    @Test
    public void testGetActors() {
        Assert.assertEquals(42, avatarInfos.getActors().size());
        Assert.assertEquals(1, sample1Infos.getActors().size());
        Assert.assertEquals("Scripter", sample1Infos.getActors().iterator().next().getRole());
        Assert.assertEquals(0, sample2Infos.getActors().size());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.MovieInfos#getDirectors()}.
     */
    @Test
    public void testGetDirectors() {
        Assert.assertEquals(1, avatarInfos.getDirectors().size());
        Assert.assertEquals("James Cameron", avatarInfos.getDirectors().toArray()[0]);
        Assert.assertEquals(0, sample2Infos.getDirectors().size());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.MovieInfos#getWriters()}.
     */
    @Test
    public void testGetWriters() {
        Assert.assertEquals(1, avatarInfos.getWriters().size());
        Assert.assertEquals("James Cameron", avatarInfos.getWriters().toArray()[0]);
        Assert.assertEquals(1, sample1Infos.getWriters().size());
        Assert.assertEquals("Yves Blusseau", sample1Infos.getWriters().toArray()[0]);
        Assert.assertEquals(0, sample2Infos.getWriters().size());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getTitle()}.
     */
    @Test
    public void testGetTitle() {
        Assert.assertEquals("Avatar", avatarInfos.getTitle());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getOriginalTitle()}.
     */
    @Test
    public void testGetOriginalTitle() {
        Assert.assertEquals("Avatar", avatarInfos.getOriginalTitle());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getProductionYear()}.
     */
    @Test
    public void testGetProductionYear() {
        Assert.assertEquals("2009", avatarInfos.getProductionYear());
        Assert.assertEquals(null, sample2Infos.getProductionYear());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getRuntime()}.
     */
    @Test
    public void testGetRuntime() {
        Assert.assertEquals(9720, avatarInfos.getRuntime());
        Assert.assertEquals(0, sample2Infos.getRuntime());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getRelease()}.
     */
    @Test
    public void testGetRelease() {
        Release release = avatarInfos.getRelease();
        Assert.assertEquals("2009-12-16", release.getReleaseDate());
        Assert.assertEquals("Twentieth Century Fox France", release.getDistributor().getName());
        Assert.assertEquals(null, sample2Infos.getRelease());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getCode()}.
     */
    @Test
    public void testGetCode() {
        Assert.assertEquals(61282, avatarInfos.getCode());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getNationalityList()}.
     */
    @Test
    public void testGetNationalityList() {
        Assert.assertEquals(1, avatarInfos.getNationalityList().size());
        Assert.assertEquals("U.S.A.", avatarInfos.getNationalityList().get(0));
        Assert.assertEquals(0, sample2Infos.getNationalityList().size());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getGenreList()}.
     */
    @Test
    public void testGetGenreList() {
        List<String> validGenres = Arrays.asList(new String[] { "Science fiction", "Aventure" });
        Assert.assertEquals(2, avatarInfos.getGenreList().size());
        Assert.assertEquals(validGenres, avatarInfos.getGenreList());
        Assert.assertEquals(0, sample2Infos.getGenreList().size());
    }

    /**
     * Test method for {@link com.moviejukebox.allocine.jaxb.Movie#getPosterUrls()}.
     */
    @Test
    public void testGetPosterUrl() {
        HashSet<String> validPosterUrls = new HashSet<String>();
        validPosterUrls.add("http://images.allocine.fr/medias/nmedia/18/64/43/65/19149062.jpg");
        validPosterUrls.add("http://images.allocine.fr/medias/nmedia/18/78/95/70/19485155.jpg");
        validPosterUrls.add("http://images.allocine.fr/medias/nmedia/18/64/43/65/19211318.jpg");
        validPosterUrls.add("http://images.allocine.fr/medias/nmedia/18/64/43/65/19150275.jpg");
        Assert.assertEquals(4, avatarInfos.getPosterUrls().size());
        Assert.assertEquals(validPosterUrls, avatarInfos.getPosterUrls());
        Assert.assertEquals(0, sample2Infos.getPosterUrls().size());
    }

}
