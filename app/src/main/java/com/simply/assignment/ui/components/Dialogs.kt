package com.simply.assignment.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.simply.assignment.R
import com.simply.assignment.data.Vehicle

@Composable
fun UnlockActionDialog(
	vehicle: Vehicle,
	onDismiss: () -> Unit,
	onConfirm: () -> Unit,
) {
	AlertDialog(
		onDismissRequest = onDismiss,
		title = {
			Text(
				text = stringResource(id = R.string.are_you_sure),
				style = MaterialTheme.typography.h6
			)
		},
		text = {
			Text(
				text = stringResource(id = R.string.confirm_unlock, vehicle.name),
				style = MaterialTheme.typography.body1
			)
		},
		confirmButton = {
			TextButton(onClick = onConfirm) {
				Text(text = stringResource(id = R.string.yes_unlock))
			}
		},
		dismissButton = {
			TextButton(onClick = onDismiss) {
				Text(text = stringResource(id = R.string.cancel))
			}
		}
	)
}