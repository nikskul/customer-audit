package ru.nikskul.customer.audit._itf.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private long version;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    public final boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        Class<?> oClass = o instanceof HibernateProxy op
            ? op.getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
        Class<?> thisClass = this instanceof HibernateProxy tp
            ? tp.getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
        if (thisClass != oClass) return false;
        DomainEntity oDomain = (DomainEntity) o;
        return getId() != null && Objects.equals(this.getId(), oDomain.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy tp
            ? tp.getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "DomainEntity{" +
            "id=" + id +
            ", version=" + version +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
