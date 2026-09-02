package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private Long id;
    private Integer balance;
    private String cardType;
    private Long userId;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Integer balance;
        private String cardType;
        private Long userId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder balance(Integer balance) {
            this.balance = balance;
            return this;
        }

        public Builder cardType(String cardType) {
            this.cardType = cardType;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Card build() {
            Card card = new Card();
            card.id = this.id;
            card.balance = this.balance;
            card.cardType = this.cardType;
            card.userId = this.userId;
            return card;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "id=" + id +
                    ", balance=" + balance +
                    ", cardType='" + cardType + '\'' +
                    ", userId=" + userId +
                    '}';
        }

    }

}
