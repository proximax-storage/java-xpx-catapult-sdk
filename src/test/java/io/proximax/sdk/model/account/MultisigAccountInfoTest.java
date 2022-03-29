/*
 * Copyright 2018 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.sdk.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import io.proximax.sdk.model.network.NetworkType;

class MultisigAccountInfoTest {
    private final PublicAccount account1 = new PublicAccount("b4f12e7c9f6946091e2cb8b6d3a12b50d17ccbbf646386ea27ce2946a7423dcf", NetworkType.TEST_NET);
    private final PublicAccount account2 = new PublicAccount("846b4439154579a5903b1459c9cf69cb8153f6d0110a7a0ed61de29ae4810bf2", NetworkType.TEST_NET);
    private final PublicAccount account3 = new PublicAccount("cf893ffcc47c33e7f68ab1db56365c156b0736824a0c1e273f9e00b8df8f01eb", NetworkType.TEST_NET);
    private final PublicAccount account4 = new PublicAccount("68b3fbb18729c1fde225c57f8ce080fa828f0067e451a3fd81fa628842b0b763", NetworkType.TEST_NET);

    @Test
    void shouldBeCreated() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Collections.singletonList(
                        account2
                ),
                Collections.emptyList()
        );
        assertEquals(1, multisigAccountInfo.getMinRemoval());
        assertEquals(2, multisigAccountInfo.getMinApproval());
        assertEquals(1, multisigAccountInfo.getCosignatories().size());
        assertEquals(account1, multisigAccountInfo.getAccount());
    }

    @Test
    void isCosignerShouldReturnTrueWhenTheAccountIsInTheCosignatoriesList() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Arrays.asList(
                        account2,
                        account3
                ),
                Collections.emptyList()
        );

        assertTrue(multisigAccountInfo.hasCosigner(account2));
    }

    @Test
    void isCosignatoryShouldReturnFalseWhenTheAccountIsNotInTHeCosignatoriesList() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Arrays.asList(
                        account2,
                        account3
                ),
                Collections.emptyList()
        );

        assertFalse(multisigAccountInfo.hasCosigner(account4));
    }

    @Test
    void isCosignerOfMultisigAccountShouldReturnTrueWhenItContainsThatAccountInMultisigList() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Collections.emptyList(),
                Arrays.asList(
                        account2,
                        account3
                )
        );

        assertTrue(multisigAccountInfo.isCosignerOfMultisigAccount(account2));
    }

    @Test
    void isCosignerOfMultisigAccountShouldReturnFalseWhenItDoesNotContainsThatAccountInMultisigList() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Collections.emptyList(),
                Arrays.asList(
                        account2,
                        account3
                )
        );

        assertFalse(multisigAccountInfo.isCosignerOfMultisigAccount(account4));
    }

    @Test
    void returnCosignersList() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Arrays.asList(
                        account2,
                        account3
                ),
                Collections.emptyList()
        );

        assertEquals(Arrays.asList(account2, account3), multisigAccountInfo.getCosignatories());
    }

    @Test
    void returnMultisigList() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                2,
                1,
                Collections.emptyList(),
                Arrays.asList(
                        account2,
                        account3
                )
        );

        assertEquals(Arrays.asList(account2, account3), multisigAccountInfo.getMultisigAccounts());
    }

    @Test
    void isMultisigShouldReturnTrueWhenMinApprovalDifferentTo0() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                0,
                1,
                Collections.emptyList(),
                Arrays.asList(
                        account2,
                        account3
                )
        );

        assertFalse(multisigAccountInfo.isMultisig());
    }

    @Test
    void isMultisigShouldReturnTrueWhenMinRemovalDifferentTo0() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                1,
                0,
                Collections.emptyList(),
                Arrays.asList(
                        account2,
                        account3
                )
        );

        assertFalse(multisigAccountInfo.isMultisig());
    }

    @Test
    void isMultisigShouldReturnFalseWhenMinRemovalAndMinApprovalEqualsTo0() {
        MultisigAccountInfo multisigAccountInfo = new MultisigAccountInfo(
                account1,
                1,
                1,
                Collections.emptyList(),
                Arrays.asList(
                        account2,
                        account3
                )
        );

        assertTrue(multisigAccountInfo.isMultisig());
    }
}