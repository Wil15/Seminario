/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "bimestre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bimestre.findAll", query = "SELECT b FROM Bimestre b"),
    @NamedQuery(name = "Bimestre.findByIdBimestre", query = "SELECT b FROM Bimestre b WHERE b.idBimestre = :idBimestre"),
    @NamedQuery(name = "Bimestre.findByNombreBimestre", query = "SELECT b FROM Bimestre b WHERE b.nombreBimestre = :nombreBimestre"),
    @NamedQuery(name = "Bimestre.findByCicloEscolar", query = "SELECT b FROM Bimestre b WHERE b.cicloEscolar = :cicloEscolar")})
public class Bimestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BIMESTRE")
    private Integer idBimestre;
    @Size(max = 45)
    @Column(name = "NOMBRE_BIMESTRE")
    private String nombreBimestre;
    @Column(name = "CICLO_ESCOLAR")
    private Integer cicloEscolar;
    @OneToMany(mappedBy = "idBimestre")
    private List<Nota> notaList;

    public Bimestre() {
    }

    public Bimestre(Integer idBimestre) {
        this.idBimestre = idBimestre;
    }

    public Integer getIdBimestre() {
        return idBimestre;
    }

    public void setIdBimestre(Integer idBimestre) {
        this.idBimestre = idBimestre;
    }

    public String getNombreBimestre() {
        return nombreBimestre;
    }

    public void setNombreBimestre(String nombreBimestre) {
        this.nombreBimestre = nombreBimestre;
    }

    public Integer getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(Integer cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBimestre != null ? idBimestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bimestre)) {
            return false;
        }
        Bimestre other = (Bimestre) object;
        if ((this.idBimestre == null && other.idBimestre != null) || (this.idBimestre != null && !this.idBimestre.equals(other.idBimestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bimestre[ idBimestre=" + idBimestre + " ]";
    }
    
}
