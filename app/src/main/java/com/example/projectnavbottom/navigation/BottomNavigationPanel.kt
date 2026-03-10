package com.example.projectnavbottom.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projectnavbottom.R

@Composable
fun BottomNavigationPanel(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit) {
    Box(modifier = Modifier.padding(5.dp)){

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
            .background(Color.Transparent),
        shape = RoundedCornerShape(20.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp

        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ItemNav(
                item = NavItem.Home,
                selected = selectedItem == NavItem.Home,
                onClick = { onItemSelected(NavItem.Home) },
                icon = painterResource(R.drawable.home)
            )
            ItemNav(
                item = NavItem.Catalog,
                selected = selectedItem == NavItem.Catalog,
                onClick = { onItemSelected(NavItem.Catalog) },
                icon = painterResource(R.drawable.location)
            )
            ItemNav(
                item = NavItem.Bookings,
                selected = selectedItem == NavItem.Bookings,
                onClick = { onItemSelected(NavItem.Bookings) },
                icon = painterResource(R.drawable.history)
            )
            ItemNav(
                item = NavItem.Profile,
                selected = selectedItem == NavItem.Profile,
                onClick = { onItemSelected(NavItem.Profile) },
                icon = painterResource(R.drawable.profile)
            )
        }
    }
}



@Composable
fun ItemNav(
    item: NavItem,
    selected: Boolean,
    onClick: () -> Unit,
    icon: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(64.dp)
            .height(56.dp)
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(5.dp)
                    .background(
                        color = Color(0xFF1F19D9),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)

                    )
            )
        } else {
            Spacer(Modifier.height(3.dp))
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            IconButton(
                onClick = { onClick() },
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 5.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = if (selected) Color(0xFF1F19D9)  else Color(0xFF4BAF47)
                )
            ) {
                Icon(
                    painter = icon,
                    contentDescription = item.title
                )
            }

        }
    }

}