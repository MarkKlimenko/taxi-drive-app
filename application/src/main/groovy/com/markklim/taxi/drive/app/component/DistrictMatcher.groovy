package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.domain.Address
import org.springframework.stereotype.Component

@Component
class DistrictMatcher {

    String defineDistrict(Address address) {
        // TODO: Добавить логику получения района из базы
        getFakeDistrict(address)
    }

    private String getFakeDistrict(Address address) {
        if (address.street == 'Volodarskogo' || address.street == 'Gorovaya'
                || address.street == 'Zelenii per' || address.street == 'Karernaya'
                || address.street == 'Komsomolskaya' || address.street == 'Lozovaya'
                || address.street == 'Nikolaevskaya' || address.street == 'Mikhaylovskaya'
                || address.street == 'Moskovskaya') {
            'grk'
        } else if (address.street == 'Borisovka' || address.street == 'Pokusa'
                || address.street == 'Parkovaya' || address.street == 'Proletarskaya'){
            'grd'
        } else if (address.street == 'Partizanskii per' || address.street == 'Vladimirskii'
                || address.street == 'Gvardeyskii per'){
            'mtf'
        } else if (address.street == 'Zarechnaya' || address.street == 'Uborevicha'
                || address.street == '3 Lugovaya'){
            'vkzl'
        } else if (address.street == 'Barabasherskaya' || address.street == 'Lineynaya'
                || address.street == 'Kuleshovskaya'){
            'slk'
        } else if (address.street == 'Krasnoznamennaya' || address.street == 'Nahimova'
                || address.street == 'Oficerskii per'){
            'dosa'
        } else if (address.street == 'Pogranichnaya' || address.street == 'Primorskaya'
                || address.street == 'Gogolya'){
            'sever'
        } else if (address.street == 'Volochaevskaya' || address.street == 'Vostochnii per'
                || address.street == 'Vostrecova' || address.street == 'Zavodskaya') {
            'crm'
        } else if (address.street == 'Kamishevii' || address.street == 'Primorskaya') {
            'rts'
        } else if (address.street == 'Rossiiskaya') {
            'most'
        } else if (address.street == 'Krasnoznamennaya' || address.street == 'Krasnogvardeyskaya') {
            'trz'
        } else if (address.street == '2 Dubovskaya') {
            'sta'
        } else if (address.street == 'Muhinskii' || address.street == '2 Naberejnaya') {
            'hleb'
        } else if (address.street == 'Parkovaya' || address.street == 'Naberejnaya') {
            'atp'
        } else if (address.street == 'Sovetskaya' || address.street == '2 Zagorodnaya') {
            'pereezd'
        } else {
            null
        }
    }
}
