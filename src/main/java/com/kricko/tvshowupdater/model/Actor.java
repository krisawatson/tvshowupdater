/*
 *      Copyright (c) 2004-2014 Matthew Altman & Stuart Boston
 *
 *      This file is part of TheTVDB API.
 *
 *      TheTVDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TheTVDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheTVDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kricko.tvshowupdater.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author matthew.altman
 * @version $Revision: 1.0 $
 */
public class Actor implements Comparable<Actor>, Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String role;
    private String image;
    private int sortOrder = 0;

    /**
     * Method getId.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Method getName.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method getRole.
     * @return String
     */
    public String getRole() {
        return role;
    }

    /**
     * Method getImage.
     * @return String
     */
    public String getImage() {
        return image;
    }

    /**
     * Method getSortOrder.
     * @return int
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Method setId.
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method setId.
     * @param id String
     */
    public void setId(String id) {
        this.id = NumberUtils.toInt(id, 0);
    }

    /**
     * Method setName.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method setRole.
     * @param role String
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method setImage.
     * @param image String
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Method setSortOrder.
     * @param sortOrder int
     */
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Method setSortOrder.
     * @param sortOrder String
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = NumberUtils.toInt(sortOrder, 0);
    }

    /**
     * Method compareTo.
     * @param other Actor
     * @return int
     */
    @Override
    public int compareTo(Actor other) {
        return sortOrder - other.getSortOrder();
    }

    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * Method hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(role)
                .append(image)
                .append(sortOrder)
                .toHashCode();
    }

    /**
     * Method equals.
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Actor) {
            final Actor other = (Actor) obj;
            return new EqualsBuilder()
                    .append(id, other.id)
                    .append(name, other.name)
                    .append(role, other.role)
                    .isEquals();
        } else {
            return false;
        }
    }
}
