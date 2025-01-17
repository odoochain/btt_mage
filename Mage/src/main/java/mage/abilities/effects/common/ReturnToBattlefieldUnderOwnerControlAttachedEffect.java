
package mage.abilities.effects.common;

import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

/**
 * @author Quercitron
 */
public class ReturnToBattlefieldUnderOwnerControlAttachedEffect extends OneShotEffect {

    public ReturnToBattlefieldUnderOwnerControlAttachedEffect() {
        super(Outcome.Neutral);
        staticText = "return that card to the battlefield under its owner's control";
    }

    protected ReturnToBattlefieldUnderOwnerControlAttachedEffect(final ReturnToBattlefieldUnderOwnerControlAttachedEffect effect) {
        super(effect);
    }

    @Override
    public ReturnToBattlefieldUnderOwnerControlAttachedEffect copy() {
        return new ReturnToBattlefieldUnderOwnerControlAttachedEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        Object object = getValue("attachedTo");
        if (object instanceof Permanent) {
            Card card = game.getCard(((Permanent) object).getId());
            if (card != null) {
                if (controller.moveCards(card, Zone.BATTLEFIELD, source, game, false, false, true, null)) {
                    return true;
                }
            }
        }

        return false;
    }
}
