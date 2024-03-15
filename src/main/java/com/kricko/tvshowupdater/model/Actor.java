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

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;

@Getter
@Setter
public class Actor implements Comparable<Actor>, Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String role;
    private String image;
    private int sortOrder = 0;

    /**
     * Method setId.
     * @param id String
     */
    public void setId(String id) {
        this.id = NumberUtils.toInt(id, 0);
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
        if (obj instanceof Actor other) {
            return new EqualsBuilder().append(id, other.id)
                                      .append(name, other.name)
                                      .append(role, other.role)
                                      .isEquals();
        } else {
            return false;
        }
    }
}
