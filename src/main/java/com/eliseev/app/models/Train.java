package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="train")
public class Train extends AbstractEntity implements Serializable {

    @NotBlank(message = "{train.name.notBlank}")
    @Size(min=1, max = 5, message = "{train.name.size}")
    private String name;
    @Digits(integer = 4, fraction = 0, message = "{train.countCoupe.digits}")
    @Column(name="coupe_places_amount")
    private int countCoupe;
    @Digits(integer = 4, fraction = 0, message = "{train.countLying.digits}")
    @Column(name="lying_places_amount")
    private int countLying;
    @Digits(integer = 4, fraction = 0, message = "{train.countCommon.digits}")
    @Column(name="common_places_amount")
    private int countCommon;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TrainRoutePiece> trainRoutePieceList = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<TrainDate> trainDates = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private Set<Carriage> carriages = new HashSet<>();

    public Train(@NotBlank(message = "{train.name.notBlank}") @Size(min = 1, max = 5, message = "{train.name.size}") String name, @Digits(integer = 4, fraction = 0, message = "{train.countCoupe.digits}") int countCoupe, @Digits(integer = 4, fraction = 0, message = "{train.countLying.digits}") int countLying, @Digits(integer = 4, fraction = 0, message = "{train.countCommon.digits}") int countCommon) {
        this.name = name;
        this.countCoupe = countCoupe;
        this.countLying = countLying;
        this.countCommon = countCommon;
    }

    public Train(long id) {
        super.id = id;
    }
}
