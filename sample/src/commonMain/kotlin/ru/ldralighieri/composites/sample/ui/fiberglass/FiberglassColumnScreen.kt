/*
 * Copyright 2023 Vladimir Raupov
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

package ru.ldralighieri.composites.sample.ui.fiberglass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ldralighieri.composites.fiberglass.column.FiberglassLazyColumn
import ru.ldralighieri.composites.fiberglass.model.FiberglassItem
import ru.ldralighieri.composites.fiberglass.model.FiberglassStickyHeaderItem
import ru.ldralighieri.composites.sample.navigation.CompositesFiberglassExampleArgs
import ru.ldralighieri.composites.sample.navigation.LocalNavigator
import ru.ldralighieri.composites.sample.navigation.Navigator
import ru.ldralighieri.composites.sample.ui.fiberglass.items.ImagesRowItem
import ru.ldralighieri.composites.sample.ui.fiberglass.items.LoremIpsumItem
import ru.ldralighieri.composites.sample.ui.fiberglass.items.SpacerItem
import ru.ldralighieri.composites.sample.ui.fiberglass.items.StickyHeaderItem
import ru.ldralighieri.composites.sample.ui.fiberglass.items.TagsFlowRowItem
import ru.ldralighieri.composites.sample.ui.fiberglass.items.imagesRowSlot
import ru.ldralighieri.composites.sample.ui.fiberglass.items.loremIpsumSlot
import ru.ldralighieri.composites.sample.ui.fiberglass.items.spacerItemSlot
import ru.ldralighieri.composites.sample.ui.fiberglass.items.stickyHeaderSlot
import ru.ldralighieri.composites.sample.ui.fiberglass.items.tagsFlowRowSlot
import ru.ldralighieri.composites.sample.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FiberglassColumnScreen(args: CompositesFiberglassExampleArgs) {
    val navigator: Navigator = LocalNavigator.current

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = "Fiberglass ${args.type}", style = AppTheme.typography.headlineSmall)
            },
            navigationIcon = {
                IconButton(onClick = { navigator.navigateBack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            },
            colors = topAppBarColors(
                containerColor = AppTheme.colors.surfaceColorAtElevation(3.dp),
                navigationIconContentColor = AppTheme.colors.onSurface,
                titleContentColor = AppTheme.colors.onSurface,
                actionIconContentColor = AppTheme.colors.onSurfaceVariant,
            ),
        )

        FiberglassColumnContent()
    }
}

@Composable
private fun FiberglassColumnContent() {
    val sections: Map<FiberglassStickyHeaderItem, List<FiberglassItem>> =
        remember {
            buildMap {
                val count = 6
                repeat(count) {
                    val number = it + 1
                    val header = StickyHeaderItem("Block №$number")
                    val items: List<FiberglassItem> =
                        buildList {
                            add(ImagesRowItem(number * 2))
                            add(TagsFlowRowItem(number * 3))
                            add(LoremIpsumItem(20 * number))
                            if (number < count) add(SpacerItem(16))
                        }
                    put(header, items)
                }
            }
        }

    FiberglassLazyColumn(
        sections = sections,
        headerSlot = stickyHeaderSlot(),
        itemSlots = mapOf(
            SpacerItem::class to spacerItemSlot(),
            LoremIpsumItem::class to loremIpsumSlot(),
            ImagesRowItem::class to imagesRowSlot(),
            TagsFlowRowItem::class to tagsFlowRowSlot(),
        ),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = WindowInsets.navigationBars
                .only(WindowInsetsSides.Bottom)
                .asPaddingValues()
                .calculateBottomPadding() + AppTheme.dimensions.bottomGuideline,
        ),
    )
}
